package labdemo.service;

import labdemo.domain.enities.Identifiable;
import labdemo.domain.models.binding.Bindable;
import labdemo.domain.models.view.Viewable;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.CrudRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

abstract class BaseService<E extends Identifiable<I>, I, R extends CrudRepository<E, I>> implements Service<E, I> {

    protected final R repository;
    protected final Validator validator;
    protected final ModelMapper mapper;
    private final Class<E> entityClass;

    protected BaseService(R repository,
                          Validator validator,
                          ModelMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
        entityClass = initEntityClass();
    }

    protected abstract Logger logger();

    //create: IN -> Bindable -> map to entity class -> repo.save
    public final <B extends Bindable<E>> boolean create(B bindingModel) {
        return validateAndCreate(bindingModel).isPresent();
    }

    @Override
    public final <B extends Bindable<E>, V extends Viewable<E>>
    Optional<V> createAndGet(B bindingModel, Class<V> viewModelClass) {
        return validateAndCreate(bindingModel)
                .map(e -> mapper.map(e, viewModelClass));
    }

    @Override
    public final <V extends Viewable<E>> Optional<V> findById(I id, Class<V> viewModelClass) {
        return repository
                .findById(id)
                .map(e -> mapper.map(e, viewModelClass));
    }

    @Override
    public final <V extends Viewable<E>> List<V> findAll(Class<V> viewModelClass) {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(t -> mapper.map(t, viewModelClass))
                .collect(Collectors.toList());
    }


    // IN -> Bindable model -> if validate ok -> map -> repo.save
    private <B extends Bindable<E>> Optional<E> validateAndCreate(B bindingModel) {
        if (validateModel(bindingModel)) {
            return Optional.of(repository.save(mapper.map(bindingModel, entityClass)));
        }
        return Optional.empty();
    }

    protected final <M extends Bindable<E>> boolean validateModel(M model) {
        Set<ConstraintViolation<M>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            String msg = "Failed validation on:\r\n\t" +
                    violations.stream()
                            .map(cv -> cv.getPropertyPath().toString()
                                    + " (" + cv.getInvalidValue() + ") " + cv.getMessage())
                            .collect(Collectors.joining("\r\n\t"));
            logger().log(Level.SEVERE, msg);
            return false;
        }
        return true;
    }


    //gets the class generic type at runtime?
    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
