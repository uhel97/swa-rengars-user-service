package cz.cvut.fel.oi.swa.rengars.userservice.rest;

import cz.cvut.fel.oi.swa.rengars.userservice.Events;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserListDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests.CreateOrUpdateUserDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.RabbitMQSender;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private RabbitMQSender rabbitMqSender;

    @Autowired
    private UserService userService;

//    @Autowired
//    public UserRestController(RabbitMQSender rabbitMqSender) {
//        this.rabbitMqSender = rabbitMqSender;
//    }
//    @Value("${app.message}")
//    private String message;

    Events event = new Events();

    @GetMapping
    public ResponseEntity<UserListDTO> getUsersList() {
        List<UserDTO> list = userService.getUsersList();
        UserListDTO userListDTO = new UserListDTO();
        list.stream().forEach(e -> userListDTO.getUserList().add(e));
        return ResponseEntity.ok(userListDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateOrUpdateUserDTO createOrUpdateUserDTO) {
        ResponseEntity<UserDTO> result = new ResponseEntity(new UserDTO(userService.createUser(createOrUpdateUserDTO)), null, HttpStatus.CREATED);
        rabbitMqSender.send(event.createEvent(result.getBody(), "NEW_USER_ACCOUNT_CREATED"));
        return result;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return new UserDTO(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody CreateOrUpdateUserDTO updateUserDTO) {
        return new ResponseEntity(new UserDTO(userService.updateUser(id, updateUserDTO)), null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<UserListDTO> getUsersListByRole(String role) {
        List<UserDTO> list = userService.getUsersList();
        UserListDTO userListDTO = new UserListDTO();
        list.stream().filter((user) -> (Objects.equals(user.getRole(), role))).forEachOrdered((user) -> {
            userListDTO.getUserList().add(user);
        });

        return ResponseEntity.ok(userListDTO);
    }

}
