package cz.cvut.fel.oi.swa.rengars.userservice.rest;

import cz.cvut.fel.oi.swa.rengars.userservice.Events;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests.RegisterUserAccountDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.RabbitMQSender;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/users")
public class RegisterRestController {

    @Autowired
    private RabbitMQSender rabbitMqSender;

    @Autowired
    private UserService userService;

    Events event = new Events();

    // register a new user's account: no all the user information are required
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerNewUserAccount(@RequestBody RegisterUserAccountDTO registerUserAccountDTO) throws IOException {
        ResponseEntity<UserDTO> result = new ResponseEntity(new UserDTO(userService.registerUserAccount(registerUserAccountDTO)), null, HttpStatus.CREATED);
        rabbitMqSender.send(event.createEvent(result.getBody(), "NEW_USER_ACCOUNT_REGISTERED"));
        return result;
    }

}
