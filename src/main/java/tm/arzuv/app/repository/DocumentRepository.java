package tm.arzuv.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tm.arzuv.app.model.Document;
import tm.arzuv.app.model.Status;
import tm.arzuv.app.model.User;

import javax.print.Doc;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>  {

    Page<Document> findAllByDnameStartingWithIgnoreCase(String dname, Pageable pageable);

    @Query(value="SELECT * FROM documents d WHERE d.user_id = :user_id AND " +
            "(lower(d.dname) LIKE lower(CONCAT(:dname,'%')) AND " +
            "d.status = :status)", nativeQuery = true)
    Page<Document> findByAuthor(@Param("user_id") int user_id,
                                String dname,
                                @Param("status") int status,
                                Pageable pageable);

    Page<Document> findByAuthorAndCreatedAfterAndStatus(User author, Date created, Status status, Pageable pageable);
    Page<Document> findByAuthorAndWhomContractStartingWithIgnoreCaseAndStatus(User author, String whomContract, Status status, Pageable pageable);
    Page<Document> findByAuthorAndWhoContractedStartingWithIgnoreCaseAndStatus(User author, String whoContracted, Status status, Pageable pageable);
    Page<Document> findByAuthorAndTermOfExecutionBetweenAndStatus(User author, Date termOfExecution, Date termOfExecution2, Status status, Pageable pageable);

    //MARK: FIND documents by author and like dname and after sort by contains
    @Query(value="SELECT * FROM documents d WHERE ((status = :status) AND (d.user_id = :user_id) AND (lower(d.dname) LIKE lower(CONCAT(:dname,'%'))))", nativeQuery = true)
    Page<Document> findByAuthorAndDnameStartingWithIgnoreCase(@Param("status") int status,
                                                              @Param("user_id") int user_id,
                                                              @Param("dname") String dname,
                                                              Pageable pageable);

    Document findById(int id);

    //MARK: FIND by like dname and sort
    Page<Document> findByDnameStartingWithIgnoreCaseAndStatus(String dname, Status status, Pageable pageable);
    
    Page<Document> findByWhomContractAndStatus(String whomContract, Status status, Pageable pageable);
    Page<Document> findByWhoContractedAndStatus(String whoContracted, Status status, Pageable pageable);

    Document save(Document document);
}
