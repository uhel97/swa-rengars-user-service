package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.EncryptionService;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Contact;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Gender;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Role;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.User;

//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;

public class UserTestHelper {

    public static final String TEST_PASSWORD_DECRYPTED = "Test!123";

    // create a test user data
    public static User getUserTestData(Long id, String username, String name, String surname, String email, String phone) {
        User user = new User();
        user.setId(id);

        user.setUsername(username);
        user.setPassword(EncryptionService.encrypt(TEST_PASSWORD_DECRYPTED, EncryptionService.DEFAULT_SALT));

        user.setName(name);
        user.setSurname(surname);
        user.setGender(Gender.MALE);

        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setPhoneNumber(phone);
        // omitted other contact fields

        user.setContact(contact);

//        user.setEnabled(true);

//        user.setCreationDt(LocalDateTime.of(2020, 2, 1, 12, 30));
//        user.setUpdatedDt(LocalDateTime.of(2020, 2, 1, 16, 45));
//        user.setLoginDt(null);

        // add the USER role
        user.setRole(Role.APPLICANT);
        
        return user;
    }

}
