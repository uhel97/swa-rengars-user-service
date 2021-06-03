package cz.cvut.fel.oi.swa.rengars.userservice;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserDTO;
import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Event;
import org.codehaus.jackson.map.ObjectMapper;

public class Events {

    private ObjectMapper obj = new ObjectMapper();
    int uniqueID = 0;

    public Event createEvent(UserDTO user, String eventType) {

        uniqueID++;

        return new Event(uniqueID, eventType, user);
    }

}
