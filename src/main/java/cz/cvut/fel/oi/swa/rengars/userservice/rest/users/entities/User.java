package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id")
    private Long id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Enumerated
    @Column(columnDefinition = "tinyint")
    private Gender gender;

    // Birth date without a time-zone in the ISO-8601 calendar system, such as 2007-12-03
    @Column(name = "birth_date")
    private java.time.LocalDate birthDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Contact contact;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @Enumerated
    @Column(columnDefinition = "tinyint")
    private Role role;

    @Basic
    private java.time.LocalDateTime createdAt;

    @Basic
    private java.time.LocalDateTime updatedAt;

}
