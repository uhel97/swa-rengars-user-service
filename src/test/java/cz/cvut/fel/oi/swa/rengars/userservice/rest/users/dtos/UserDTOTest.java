package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Role;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Contact;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.User;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Gender;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.ContactDTO;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.*;

public class UserDTOTest {

    @Test
    public void userDTOTestConstructor1() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUsername");
        user.setName("testName");
        user.setSurname("testSurname");
        user.setGender(Gender.MALE);
        user.setRole(Role.HEADHUNTER);

        Contact contactInput = new Contact();
        contactInput.setEmail("email");
        contactInput.setPhoneNumber("+3531122334455");
        contactInput.setLinkedin("linkedin");

        user.setContact(contactInput);

        LocalDateTime createdAt = LocalDateTime.of(2020, 2, 1, 12, 30);
        user.setCreatedAt(createdAt);

        LocalDateTime updatedAt = LocalDateTime.of(2020, 2, 1, 16, 45);
        user.setUpdatedAt(updatedAt);

        UserDTO userDTO = new UserDTO(user);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getSurname(), user.getSurname());

        // contact
        ContactDTO contactDTO = userDTO.getContactDTO();
        assertNotNull(contactDTO);

        assertEquals(userDTO.getContactDTO().getEmail(), user.getContact().getEmail());
        assertEquals(userDTO.getContactDTO().getPhoneNumber(), user.getContact().getPhoneNumber());
        assertEquals(userDTO.getContactDTO().getLinkedin(), user.getContact().getLinkedin());

        assertEquals(createdAt, userDTO.getCreatedAt());
        assertEquals(updatedAt, userDTO.getUpdatedAt());

        assertEquals(Role.getValidRole(userDTO.getRole()), user.getRole());
    }

    @Test
    public void userDTOTestConstructor2() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUsername");
        user.setName("testName");
        user.setSurname("testSurname");
        user.setGender(Gender.MALE);

        Contact contactInput = new Contact();
        contactInput.setEmail("email");
        contactInput.setPhoneNumber("+3531122334455");
        contactInput.setLinkedin("linkedin");

        user.setContact(contactInput);

        LocalDateTime createdAt = LocalDateTime.of(2020, 2, 1, 12, 30);
        user.setCreatedAt(createdAt);

        LocalDateTime updatedAt = LocalDateTime.of(2020, 2, 1, 16, 45);
        user.setUpdatedAt(updatedAt);

        // create role
        Role roleApplicant = Role.APPLICANT;

        user.setRole(roleApplicant);

        UserDTO userDTO = new UserDTO(user);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getSurname(), user.getSurname());

        // contact
        ContactDTO contactDTO = userDTO.getContactDTO();
        assertNotNull(contactDTO);

        assertEquals(userDTO.getContactDTO().getEmail(), user.getContact().getEmail());
        assertEquals(userDTO.getContactDTO().getPhoneNumber(), user.getContact().getPhoneNumber());
        assertEquals(userDTO.getContactDTO().getLinkedin(), user.getContact().getLinkedin());

        assertEquals(createdAt, userDTO.getCreatedAt());
        assertEquals(updatedAt, userDTO.getUpdatedAt());

        assertEquals(Role.getValidRole(userDTO.getRole()), roleApplicant);
    }

}
