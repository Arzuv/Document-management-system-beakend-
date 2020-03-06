package tm.arzuv.app.repository;

import java.util.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.arzuv.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email); 
    List<User> findAll();
    List<User> findAll(Sort sort);
    List<User> findByLastnameOrFirstname(String firstname, String lastname);
    User findById(int id);
    User save(User u);
    void deleteById(int id);
    boolean existsById(int id);
}