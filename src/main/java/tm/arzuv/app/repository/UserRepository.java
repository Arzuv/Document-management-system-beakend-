package tm.arzuv.app.repository;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tm.arzuv.app.model.Status;
import tm.arzuv.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndStatus(String email, Status status);
    Page<User> findAll(Pageable pageable);
    User findById(int id);
    // SELECT DISTINCT u FROM users u RIGHT JOIN documents d ON u.id = d.user_id
    @Query(value="SELECT * FROM users WHERE (status = :status) AND (id IN (SELECT user_id FROM documents))", nativeQuery = true)
    Page<User> findAuthors(@Param("status") int status, Pageable pageable);
}
