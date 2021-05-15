package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions.InvalidUsernameException;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions.UserNotFoundException;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions.InvalidUserIdentifierException;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions.InvalidEmailException;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions.InvalidUserDataException;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Address;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.User;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Contact;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Gender;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Role;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests.CreateOrUpdateUserDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests.RegisterUserAccountDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.repositories.AddressRepository;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.repositories.ContactRepository;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.repositories.UserRepository;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.validation.EmailValidator;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.validation.PasswordValidator;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.validation.PhoneValidator;
//import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Value("${microservice.security.salt}")
    private String salt;

    private PasswordValidator passwordValidator;
    private EmailValidator emailValidator;
    private PhoneValidator phoneValidator;

    public UserService() {
        passwordValidator = new PasswordValidator();
        emailValidator = new EmailValidator();
        phoneValidator = new PhoneValidator();
    }

    public List<UserDTO> getUsersList() {
        ArrayList<UserDTO> listDto = new ArrayList<>();
        Iterable<User> list = getUserList();
        list.forEach(e -> listDto.add(new UserDTO(e)));
        return listDto;
    }

    public User getUserById(Long id) {
        if (id == null) {
            throw new InvalidUserIdentifierException("User Id cannot be null");
        }
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        throw new UserNotFoundException(String.format("User not found for Id = %s", id));
    }

    public User getUserByUsername(String username) {
        if (username == null) {
            throw new InvalidUsernameException("username cannot be null");
        }
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        if (email == null) {
            throw new InvalidEmailException("email cannot be null");
        }
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User registerUserAccount(RegisterUserAccountDTO registerUserAccountDTO) {
        if (registerUserAccountDTO == null) {
            throw new InvalidUserDataException("User account data cannot be null");
        }

        checkIfUsernameNotUsed(registerUserAccountDTO.getUsername());
        passwordValidator.checkPassword(registerUserAccountDTO.getPassword());
        emailValidator.checkEmail(registerUserAccountDTO.getEmail());

        checkIfEmailNotUsed(registerUserAccountDTO.getEmail());

        // create the new user account: not all the user information required
        User user = new User();
        user.setUsername(registerUserAccountDTO.getUsername());
        user.setPassword(EncryptionService.encrypt(registerUserAccountDTO.getPassword(), salt));

        user.setName(registerUserAccountDTO.getName());
        user.setSurname(registerUserAccountDTO.getSurname());

        // set gender
        Gender gender = Gender.getValidGender(registerUserAccountDTO.getGender());
        user.setGender(gender);

        // set role
        Role role = Role.getValidRole(registerUserAccountDTO.getRole());
        user.setRole(role);

        user.setCreatedAt(LocalDateTime.now());

        User userCreated = userRepository.save(user);

        // set contact
        Contact contact = new Contact();
        contact.setEmail(registerUserAccountDTO.getEmail());

        addContactOnUser(userCreated, contact);

        // set empty address
        addAddressOnUser(userCreated, new Address());

        userCreated = userRepository.save(userCreated);

        log.info(String.format("User %s has been created.", userCreated.getId()));
        return userCreated;
    }

    // check if the username has not been registered
    public void checkIfUsernameNotUsed(String username) {
        User userByUsername = getUserByUsername(username);
            if (userByUsername != null) {
                String msg = String.format("The username %s it's already in use from another user with ID = %s",
                        userByUsername.getUsername(), userByUsername.getId());
                log.error(msg);
            throw new InvalidUserDataException(msg);
        }
    }

    // check if the email has not been registered
    public void checkIfEmailNotUsed(String email) {
        User userByEmail = getUserByEmail(email);
        if (userByEmail != null) {
            String msg = String.format("The email %s it's already in use from another user with ID = %s",
                    userByEmail.getContact().getEmail(), userByEmail.getId());
            log.error(msg);
            throw new InvalidUserDataException(String.format("This email %s it's already in use.",
                    userByEmail.getContact().getEmail()));
        }
    }

    @Transactional
    public User createUser(CreateOrUpdateUserDTO createUserDTO) {
        if (createUserDTO == null) {
            throw new InvalidUserDataException("User account data cannot be null");
        }

        checkIfUsernameNotUsed(createUserDTO.getUsername());
        checkIfEmailNotUsed(createUserDTO.getEmail());
        passwordValidator.checkPassword(createUserDTO.getPassword());
        emailValidator.checkEmail(createUserDTO.getEmail());
        phoneValidator.checkPhone(createUserDTO.getPhoneNumber());

        // create the user
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(EncryptionService.encrypt(createUserDTO.getPassword(), salt));

        user.setName(createUserDTO.getName());
        user.setSurname(createUserDTO.getSurname());

        // set gender
        Gender gender = Gender.getValidGender(createUserDTO.getGender());
        user.setGender(gender);

        // set role
        Role role = Role.getValidRole(createUserDTO.getRole());
        user.setRole(role);

        // date of birth
        user.setBirthDate(createUserDTO.getBirthDate());

        user.setCreatedAt(LocalDateTime.now());

        User userCreated = userRepository.save(user);

        // set contact
        Contact contact = new Contact();
        contact.setEmail(createUserDTO.getEmail());
        contact.setPhoneNumber(createUserDTO.getPhoneNumber());
        contact.setLinkedin(createUserDTO.getLinkedin());

        addContactOnUser(userCreated, contact);

        // set address
        Address address = new Address();
        address.setAddress(createUserDTO.getAddress());
        address.setAddress2(createUserDTO.getAddress2());
        address.setCity(createUserDTO.getCity());
        address.setCountry(createUserDTO.getCountry());
        address.setZipCode(createUserDTO.getZipCode());

        addAddressOnUser(userCreated, address);

        userCreated = userRepository.save(userCreated);

        log.info(String.format("User %s has been created.", userCreated.getId()));
        return userCreated;
    }

    public void addContactOnUser(User user, Contact contact) {
        contact.setUser(user);
        user.setContact(contact);

        log.debug(String.format("Contact information set on User %s .", user.getId()));
    }

    public void addAddressOnUser(User user, Address address) {
        address.setUser(user);
        user.setAddress(address);

        log.debug(String.format("Address information set on User %s .", user.getId()));
    }

    @Transactional
    public User updateUser(Long id, CreateOrUpdateUserDTO updateUserDTO) {
        if (id == null) {
            throw new InvalidUserIdentifierException("Id cannot be null");
        }
        if (updateUserDTO == null) {
            throw new InvalidUserDataException("User account data cannot be null");
        }

        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException(String.format("The user with Id = %s doesn't exists", id));
        }
        User user = userOpt.get();

        // check if the username has not been registered
        User userByUsername = getUserByUsername(updateUserDTO.getUsername());
        if (userByUsername != null) {
            // check if the user's id is different than the actual user
            if (!user.getId().equals(userByUsername.getId())) {
                String msg = String.format("The username %s it's already in use from another user with ID = %s",
                        updateUserDTO.getUsername(), userByUsername.getId());
                log.error(msg);
                throw new InvalidUserDataException(msg);
            }
        }

        passwordValidator.checkPassword(updateUserDTO.getPassword());
        emailValidator.checkEmail(updateUserDTO.getEmail());
        phoneValidator.checkPhone(updateUserDTO.getPhoneNumber());

        // check if the new email has not been registered yet
        User userEmail = getUserByEmail(updateUserDTO.getEmail());
        if (userEmail != null) {
            // check if the user's email is different than the actual user
            if (!user.getId().equals(userEmail.getId())) {
                String msg = String.format("The email %s it's already in use from another user with ID = %s",
                        updateUserDTO.getEmail(), userEmail.getId());
                log.error(msg);
                throw new InvalidUserDataException(msg);
            }
        }

        // update the user
        user.setUsername(updateUserDTO.getUsername());

        // using the user's salt to secure the new validated password
        user.setPassword(EncryptionService.encrypt(updateUserDTO.getPassword(), salt));
        user.setName(updateUserDTO.getName());
        user.setSurname(updateUserDTO.getSurname());

        // set gender
        Gender gender = Gender.getValidGender(updateUserDTO.getGender());
        user.setGender(gender);

        // set role
        Role role = Role.getValidRole(updateUserDTO.getRole());
        user.setRole(role);

        // date of birth
        user.setBirthDate(updateUserDTO.getBirthDate());

        // set contact: entity always present
        Contact contact = user.getContact();
        contact.setEmail(updateUserDTO.getEmail());
        contact.setPhoneNumber(updateUserDTO.getPhoneNumber());
        contact.setLinkedin(updateUserDTO.getLinkedin());

        user.setUpdatedAt(LocalDateTime.now());

        // set address
        Address address = user.getAddress();
        if (address == null) {
            address = new Address();
        }
        address.setAddress(updateUserDTO.getAddress());
        address.setAddress2(updateUserDTO.getAddress2());
        address.setCity(updateUserDTO.getCity());
        address.setCountry(updateUserDTO.getCountry());
        address.setZipCode(updateUserDTO.getZipCode());

        addAddressOnUser(user, address);

        User userUpdated = userRepository.save(user);
        log.info(String.format("User %s has been updated.", user.getId()));

        return userUpdated;
    }

    public Iterable<User> getUserList() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (id == null) {
            throw new InvalidUserIdentifierException("Id cannot be null");
        }

        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException(String.format("User not found with Id = %s", id));
        }

        userRepository.deleteById(id);
        log.info(String.format("User %s has been deleted.", id));
    }

}
