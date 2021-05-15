package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Contact;
import lombok.Data;

@Data
public class ContactDTO implements java.io.Serializable {

    public ContactDTO() {
        // empty constructor
    }

    public ContactDTO(Contact contact) {
        if (contact != null) {
            this.email = contact.getEmail();
            this.phoneNumber = contact.getPhoneNumber();
            this.linkedin = contact.getLinkedin();
        }
    }

    private String email;
    private String phoneNumber;
    private String linkedin;
}
