package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.exceptions.InvalidGenderException;

public enum Role {

    APPLICANT(1), HEADHUNTER(2);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public static Role getValidRole(String roleName) {
        Role role;

        try {
            role = Role.valueOf(roleName);

        } catch(IllegalArgumentException ex) {

            throw new InvalidGenderException(String.format("Invalid role string %s. Are supported only: APPLICANT or HEADHUNTER strings", roleName));
        }

        return role;
    }

}
