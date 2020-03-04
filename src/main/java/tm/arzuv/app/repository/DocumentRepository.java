package tm.arzuv.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tm.arzuv.app.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>  {
    List<Document> findAll();
}