package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions;

public class RoleInUseException extends RuntimeException {

    public RoleInUseException(String message) {
        super(message);
    }

}
