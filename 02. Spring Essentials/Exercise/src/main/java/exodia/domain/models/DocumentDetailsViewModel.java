package exodia.domain.models;

import exodia.domain.api.Viewable;
import exodia.domain.entities.Document;

import java.io.Serializable;

public class DocumentDetailsViewModel implements Viewable<Document>, Serializable {

    //hint: serialVersionUID is a unique identifier for Serializable classes.
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String content;

    public DocumentDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
