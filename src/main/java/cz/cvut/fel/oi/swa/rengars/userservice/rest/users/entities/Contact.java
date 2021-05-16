package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="linkedin")
    private String linkedin;

    @OneToOne
    @MapsId
    private User user;

}
