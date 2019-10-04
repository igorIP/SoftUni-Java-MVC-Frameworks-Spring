package exodia.domain.entities;

import exodia.domain.api.Identifiable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * <p>
 * Use space optimized BINARY(16) instead of CHAR/VARCHAR(36) database format
 * <p>
 * Use AccessType.PROPERTY for id as best practice to avoid LazyInitializationException
 * <p>
 * Use private setter to prevent mutability
 * <ul>
 * Useful SQL commands:
 * <li>
 * Select by id:
 * <br>
 * SELECT * FROM table_name where id = UUID_TO_BIN('24abff5d-f3d9-46df-a5ad-361fcbfe045e');
 * <li>
 * List IDs as UUID:
 * <br>
 * SELECT BIN_TO_UUID(id) FROM table_name
 */
@MappedSuperclass //todo: BaseUuidEntity<I> implements Identifiable<I> maybe should not implement <I>.
abstract class BaseUuidEntity implements Identifiable<UUID> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(unique = true, nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    @Access(AccessType.PROPERTY)
    private UUID id;

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }
}
