package exodia.repository;

import exodia.domain.entities.Document;
import exodia.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {

    @Query("SELECT d.id as id, d.title as title FROM Document AS d")
    List<Tuple> findAllShortView();
}
