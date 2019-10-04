package exodia.domain.models;

import exodia.domain.api.Viewable;
import exodia.domain.entities.Document;

public class DocumentTitleAndIdViewModel implements Viewable<Document> {

    private String id;
    private String title;

    public static DocumentTitleAndIdViewModel from(String id, String title) {
        DocumentTitleAndIdViewModel document = new DocumentTitleAndIdViewModel();
        document.id = id;
        document.title = title;
        return document;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
