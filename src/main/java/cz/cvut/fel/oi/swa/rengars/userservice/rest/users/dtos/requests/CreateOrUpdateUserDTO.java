package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Create or modify user data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CreateOrUpdateUserDTO implements Serializable {

    private String username;
    private String password;

    private String name;
    private String surname;
    private String gender;
    private java.time.LocalDate birthDate;
    
//    private java.time.LocalDate createdAt;
//    private java.time.LocalDate updateddAt;

    // contact information
    private String email;
    private String phoneNumber;
    private String linkedin;

    // address information
    private String address;
    private String address2;
    private String city;
    private String country;
    private String zipCode;

    // role inforamtion
    private String role;

    @Override
    public String toString() {
        return "CreateOrUpdateUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
