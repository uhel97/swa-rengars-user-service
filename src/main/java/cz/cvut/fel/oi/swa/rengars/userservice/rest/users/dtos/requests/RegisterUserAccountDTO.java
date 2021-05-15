package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Support the creation of a new user account with a minimum set of required data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserAccountDTO implements Serializable {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private String role;
//    private java.time.LocalDate createdAt;

}
