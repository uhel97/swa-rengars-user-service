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
//        user.setEnabled(true);
        user.setGender(Gender.MALE);
//        user.setEnabled(true);
//        user.setSecured(false);

        Contact contactInput = new Contact();
        contactInput.setEmail("email");
        contactInput.setPhoneNumber("+3531122334455");
//        contactInput.setSkype("skype");
//        contactInput.setFacebook("facebook");
        contactInput.setLinkedin("linkedin");
//        contactInput.setWebsite("www.test.com");
//        contactInput.setNote("Test note");

        user.setContact(contactInput);

        LocalDateTime createdAt = LocalDateTime.of(2020, 2, 1, 12, 30);
        user.setCreatedAt(createdAt);

        LocalDateTime updatedAt = LocalDateTime.of(2020, 2, 1, 16, 45);
        user.setUpdatedAt(updatedAt);

        Role roleHeadhunter = new Role(Role.HEADHUNTER, "HEADHUNTER");

        user.setRole(roleHeadhunter);

        UserDTO userDTO = new UserDTO(user);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getSurname(), user.getSurname());

//        assertTrue(userDTO.isEnabled());
//        assertTrue(!userDTO.isSecured());

        // contact
        ContactDTO contactDTO = userDTO.getContactDTO();
        assertNotNull(contactDTO);

        assertEquals(userDTO.getContactDTO().getEmail(), user.getContact().getEmail());
        assertEquals(userDTO.getContactDTO().getPhoneNumber(), user.getContact().getPhoneNumber());
//        assertEquals(userDTO.getContactDTO().getSkype(), user.getContact().getSkype());
//        assertEquals(userDTO.getContactDTO().getFacebook(), user.getContact().getFacebook());
        assertEquals(userDTO.getContactDTO().getLinkedin(), user.getContact().getLinkedin());
//        assertEquals(userDTO.getContactDTO().getWebsite(), user.getContact().getWebsite());
//        assertEquals(userDTO.getContactDTO().getContactNote(), user.getContact().getNote());

//        assertEquals(userDTO.isEnabled(), user.isEnabled());

        assertEquals(createdAt, userDTO.getCreatedAt());
        assertEquals(updatedAt, userDTO.getUpdatedAt());
//        assertEquals(null, userDTO.getLoginDt());

//        assertEquals(userDTO.getRoleDTO(), roleHeadhunter);
    }

    @Test
    public void userDTOTestConstructor2() {
        // test enabled and disabled permissions
        User user = new User();
        user.setId(1L);
        user.setUsername("testUsername");
        user.setName("testName");
        user.setSurname("testSurname");
//        user.setEnabled(true);
        user.setGender(Gender.MALE);
//        user.setEnabled(true);
//        user.setSecured(false);

        Contact contactInput = new Contact();
        contactInput.setEmail("email");
        contactInput.setPhoneNumber("+3531122334455");
//        contactInput.setSkype("skype");
//        contactInput.setFacebook("facebook");
        contactInput.setLinkedin("linkedin");
//        contactInput.setWebsite("www.test.com");
//        contactInput.setNote("Test note");

        user.setContact(contactInput);

        LocalDateTime createdAt = LocalDateTime.of(2020, 2, 1, 12, 30);
        user.setCreatedAt(createdAt);

        LocalDateTime updatedAt = LocalDateTime.of(2020, 2, 1, 16, 45);
        user.setUpdatedAt(updatedAt);

        // create role
        Role roleApplicant = new Role(Role.APPLICANT, "APPLICANT");

//        Permission p1 = new Permission(1L, "LOGIN", true, "Login");
//        Permission p2 = new Permission(2L, "VIEW_PROFILE", true, "View Profile");
//        Permission p3 = new Permission(3L, "ADMIN_STATISTICS", false, "View statistical graphs");
//        Permission p4 = new Permission(4L, "ADMIN_PROFILES", true, "Manage users");

//        roleUser.getPermissions().add(p1);
//        roleUser.getPermissions().add(p2);

//        roleAdmin.getPermissions().add(p3);
//        roleAdmin.getPermissions().add(p4);

        user.setRole(roleApplicant);

        UserDTO userDTO = new UserDTO(user);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getSurname(), user.getSurname());

//        assertTrue(userDTO.isEnabled());
//        assertTrue(!userDTO.isSecured());

        // contact
        ContactDTO contactDTO = userDTO.getContactDTO();
        assertNotNull(contactDTO);

        assertEquals(userDTO.getContactDTO().getEmail(), user.getContact().getEmail());
        assertEquals(userDTO.getContactDTO().getPhoneNumber(), user.getContact().getPhoneNumber());
//        assertEquals(userDTO.getContactDTO().getSkype(), user.getContact().getSkype());
//        assertEquals(userDTO.getContactDTO().getFacebook(), user.getContact().getFacebook());
        assertEquals(userDTO.getContactDTO().getLinkedin(), user.getContact().getLinkedin());
//        assertEquals(userDTO.getContactDTO().getWebsite(), user.getContact().getWebsite());
//        assertEquals(userDTO.getContactDTO().getContactNote(), user.getContact().getNote());

//        assertEquals(userDTO.isEnabled(), user.isEnabled());

        assertEquals(createdAt, userDTO.getCreatedAt());
        assertEquals(updatedAt, userDTO.getUpdatedAt());
//        assertEquals(null, userDTO.getLoginDt());

//        assertEquals(userDTO.getRoleDTO(), roleApplicant);

//        assertEquals(2, userDTO.getRoles().size());
//        assertTrue(userDTO.getRoles().contains("USER"));
//        assertTrue(userDTO.getRoles().contains("ADMINISTRATOR"));
//
//        assertEquals(2, userDTO.getRoles().size());
//        assertEquals(3, userDTO.getPermissions().size());
//
//        assertEquals(3, userDTO.getPermissions().size());
//        assertTrue(userDTO.getPermissions().contains("LOGIN"));
//        assertTrue(userDTO.getPermissions().contains("VIEW_PROFILE"));
//
//        assertTrue(userDTO.getPermissions().contains("ADMIN_PROFILES"));
//        assertFalse(userDTO.getPermissions().contains("ADMIN_STATISTICS"));
    }

}
