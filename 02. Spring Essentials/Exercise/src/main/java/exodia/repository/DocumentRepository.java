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

    /**
     * Tuple is Interface for extracting the elements of a query result tuple.
     * The term is used mostly when the items are of different types.
     * Tuples have an "alias" property that gets filled out by the query, which you can then use later.
     * TupleElement supports Java Generics.
     * One way often used in Java is just to have an Object[].
     * more on https://stackoverflow.com/questions/36078051/in-jpa-relational-databases-and-etc-what-is-a-tuple?noredirect=1&lq=1
     */
    @Query("SELECT d.id as id, d.title as title FROM Document AS d")
    List<Tuple> findAllShortView();
}
