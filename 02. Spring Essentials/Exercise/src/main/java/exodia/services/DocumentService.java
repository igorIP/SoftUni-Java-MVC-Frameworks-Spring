package exodia.services;


import exodia.domain.entities.Document;
import exodia.domain.models.DocumentDetailsViewModel;
import exodia.domain.models.DocumentTitleAndIdViewModel;
import exodia.domain.models.binding.DocumentScheduleBindingModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentService extends Service<Document, UUID> {

    Optional<DocumentDetailsViewModel> schedule(DocumentScheduleBindingModel bindingModel);

    boolean print(String id);

    List<DocumentTitleAndIdViewModel> findAllShortView();
}
