package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserDTO;

public class Event {

    private int id;
    private String eventType;
    private UserDTO payload;

    public Event(int id, String eventType, UserDTO user) {
        this.id = id;
        this.eventType = eventType;
        this.payload = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public UserDTO getUser() {
        return payload;
    }

    public void setUser(UserDTO user) {
        this.payload = user;
    }
}
