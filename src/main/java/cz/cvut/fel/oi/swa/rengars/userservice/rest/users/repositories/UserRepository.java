package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.repositories;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.contact.email = :email")
    User findByEmail(@Param("email") String email);

    User findByUsername(String username);

}
