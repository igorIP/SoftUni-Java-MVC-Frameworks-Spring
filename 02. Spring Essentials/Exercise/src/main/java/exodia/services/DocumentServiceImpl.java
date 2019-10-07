package exodia.services;

import exodia.domain.entities.Document;
import exodia.domain.models.DocumentDetailsViewModel;
import exodia.domain.models.DocumentTitleAndIdViewModel;
import exodia.domain.models.binding.DocumentScheduleBindingModel;
import exodia.repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentServiceImpl extends BaseService<Document, UUID, DocumentRepository> implements DocumentService {

    @Autowired
    protected DocumentServiceImpl(DocumentRepository repository,
                                  Validator validator,
                                  ModelMapper mapper) {
        super(repository, validator, mapper);
    }

    @Override
    public Optional<DocumentDetailsViewModel> schedule(DocumentScheduleBindingModel bindingModel) {
        return super.createAndGet(bindingModel, DocumentDetailsViewModel.class);
    }

    @Override
    public boolean print(String id) {
        return deleteById(UUID.fromString(id));
    }

    @Override
    public List<DocumentTitleAndIdViewModel> findAllShortView() {
        return repository
                .findAllShortView()
                .stream()
                .map(el ->
                        DocumentTitleAndIdViewModel.from(
                                el.get("id").toString(),
                                el.get("title").toString())
                )
                .collect(Collectors.toList());
    }
}
