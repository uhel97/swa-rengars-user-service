package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
public class Role {

    public static final long APPLICANT = 1;
    public static final long HEADHUNTER = 2;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id")
    private Long id;

    @Column(name="role", nullable = false)
    private String role;

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }
    
    @OneToOne
    @MapsId
    private User user;

//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(name = "permissions_roles",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "permission_id"))
//    private List<Permission> permissions= new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role )) return false;
        return id != null && id.equals(((Role) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}