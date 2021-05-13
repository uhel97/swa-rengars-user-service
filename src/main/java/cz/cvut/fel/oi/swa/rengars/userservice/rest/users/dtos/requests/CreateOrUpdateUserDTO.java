package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Create or modify user data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrUpdateUserDTO implements Serializable {

    private String username;
    private String password;

    private String name;
    private String surname;
    private String gender;
    private java.time.LocalDate birthDate;
    
    private java.time.LocalDate createdAt;
    private java.time.LocalDate updateddAt;
    
    private Role role;

//    private boolean enabled;
//    private boolean secured;

//    private String note;

    // contact information
    private String email;
    private String phoneNumber;
//    private String skype;
//    private String facebook;
    private String linkedin;
//    private String website;
//    private String contactNote;

    // address information
    private String address;
    private String address2;
    private String city;
    private String country;
    private String zipCode;

}
