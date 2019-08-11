package melih.demircan.persistence.dao;

import melih.demircan.persistence.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
}
