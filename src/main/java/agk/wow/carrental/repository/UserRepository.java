package agk.wow.carrental.repository;

import agk.wow.carrental.model.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByEmail(String email);
}