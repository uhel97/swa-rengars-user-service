package cz.cvut.fel.oi.swa.rengars.userservice;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserDTO;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

public class Events {

    private ObjectMapper obj = new ObjectMapper();
    int uniqueID = 0;

    public String createEvent(UserDTO user, String eventType) throws IOException {

        String jsonStr = obj.writeValueAsString(user);

        uniqueID++;

        return "{" +
                "\"id\":" + uniqueID +
                ",\"eventType\":" + eventType +
                ",\"payload\":" + jsonStr +
                "}";
    }

}
