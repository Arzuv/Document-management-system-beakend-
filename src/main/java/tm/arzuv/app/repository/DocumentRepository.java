package tm.arzuv.app.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tm.arzuv.app.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>  {
    List<Document> findAll();
    List<Document> findAll(Sort sort);
    Document findById(int id);
    Document findByDname(String dname);
    List<Document> findByWhomContract(String whomContract);
    List<Document> findByWhoContracted(String whoContracted);
    Document save(Document d);
    boolean deleteById(int id);
}