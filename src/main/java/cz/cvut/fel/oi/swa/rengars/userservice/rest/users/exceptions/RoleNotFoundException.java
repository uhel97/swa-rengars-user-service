package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {
        super(message);
    }

}
