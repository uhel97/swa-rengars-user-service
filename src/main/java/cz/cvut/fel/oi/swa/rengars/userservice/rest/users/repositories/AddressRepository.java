package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.repositories;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
