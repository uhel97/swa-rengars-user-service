package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos;

//import com.giassi.microservice.demo2.rest.users.entities.Permission;
//import com.giassi.microservice.demo2.rest.users.entities.Role;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;

@Data
public class UserDTO implements Serializable {

    public UserDTO() {
        // empty constructor
//        roles = new ArrayList<>();
//        permissions = new ArrayList<>();
    }

    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.surname = user.getSurname();
            this.gender = user.getGender().name();

            this.birthDate = user.getBirthDate();

//            this.enabled = user.isEnabled();

//            this.note = user.getNote();

            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
//            this.loginDt = user.getLoginDt();

//            this.secured = user.isSecured();

            // contact, if set
            if (user.getContact() != null) {
                this.contactDTO = new ContactDTO(user.getContact());
            }

            // address, if set
            if (user.getAddress() != null) {
                this.addressDTO = new AddressDTO(user.getAddress());
            }
            
            // role, if set
            if (user.getRole() != null) {
                this.roleDTO = new RoleDTO(user.getRole());
            }

            // Because the permissions can be associated to more than one roles i'm creating two String arrays
            // with the distinct keys of roles and permissions.
//            roles = new ArrayList<>();
//            permissions = new ArrayList<>();
//
//            for (Role role : user.getRoles()) {
//                roles.add(role.getRole());
//                for (Permission p : role.getPermissions()) {
//                    String key = p.getPermission();
//                    if ((!permissions.contains(key)) && (p.isEnabled())) {
//                        // add the permission only if enabled
//                        permissions.add(key);
//                    }
//                }
//            }

        }
    }

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String gender;
    private java.time.LocalDate birthDate;

//    private boolean enabled;

//    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private LocalDateTime loginDt;

//    private boolean secured;

    private ContactDTO contactDTO;
    private AddressDTO addressDTO;
    private RoleDTO roleDTO;

    // permissions and roles list
//    private List<String> roles;
//    private List<String> permissions;

}
