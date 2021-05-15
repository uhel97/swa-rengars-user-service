package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos;

//import com.giassi.microservice.demo2.rest.users.entities.Permission;
//import com.giassi.microservice.demo2.rest.users.entities.Role;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {

    public UserDTO() {
        // empty constructor
    }

    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.surname = user.getSurname();
            this.gender = user.getGender().name();

            this.birthDate = user.getBirthDate();

            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();

            // contact, if set
            if (user.getContact() != null) {
                this.contactDTO = new ContactDTO(user.getContact());
            }

            // address, if set
            if (user.getAddress() != null) {
                this.addressDTO = new AddressDTO(user.getAddress());
            }

            this.role = user.getRole().name();

        }
    }

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String gender;
    private java.time.LocalDate birthDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ContactDTO contactDTO;
    private AddressDTO addressDTO;
    private String role;

}
