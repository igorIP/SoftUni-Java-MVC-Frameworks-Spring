package exodia.domain.models.binding;

import exodia.domain.api.Bindable;
import exodia.domain.entities.Document;

public class DocumentScheduleBindingModel implements Bindable<Document> {

    private String title;
    private String content;

    public DocumentScheduleBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
