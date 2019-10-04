package exodia.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document extends BaseUuidEntity {

    @Column(nullable = false)
    private String title;

    @Lob//A Lob may be either a binary or character type.
    @Column(nullable = false)
    private String content;

    public Document() {
    }
}
