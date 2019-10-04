package exodia.services;

import exodia.domain.api.Bindable;
import exodia.domain.api.Identifiable;
import exodia.domain.api.Viewable;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract class, implements common functionality for services
 *
 * @param <E> Entity
 * @param <I> ID class used by Entity
 * @param <R> Repository for Entity
 */

@Transactional
public abstract class BaseService<E extends Identifiable<I>, I, R extends JpaRepository<E, I>> implements Service<E, I> {

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
        this.entityClass = initEntityClass();
    }

    @Override
    public <B extends Bindable<E>> boolean create(B bindingModel) {
        return validateAndCreate(bindingModel)
                .isPresent();
    }

    @Override
    public <B extends Bindable<E>, V extends Viewable<E>>
    Optional<V> createAndGet(B bindingModel, Class<V> viewModelClass) {
        return validateAndCreate(bindingModel)
                .map(el -> mapper.map(el, viewModelClass));
    }

    @Override
    public <V extends Viewable<E>>
    Optional<V> findById(I id, Class<V> viewModelClass) {
        return repository
                .findById(id)
                .map(el -> mapper.map(el, viewModelClass));
    }

    @Override
    public <V extends Viewable<E>> List<V> findAll(Class<V> viewModelClass) {
        return repository
                .findAll()
                .stream()
                .map(el -> mapper.map(el, viewModelClass))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(I id) {
        return deleteByIdAndGet(id)
                .isPresent();
    }

    @Override
    public <V extends Viewable<E>>
    Optional<V> deleteByIdAndGet(I id, Class<V> viewModelClass) {
        return deleteByIdAndGet(id)
                .map(el -> mapper.map(el, viewModelClass));
    }

    private <T> boolean validateModel(T model) {
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            //todo: logger to take this msg String
            return false;
        }
        return true;
    }

    private <B extends Bindable<E>> Optional<E> validateAndCreate(B bindingModel) {
        if (validateModel(bindingModel)) {
            return Optional.of(repository.save(mapper.map(bindingModel, entityClass)));
        }
        return Optional.empty();
    }

    private Optional<E> deleteByIdAndGet(I id) {
        return repository
                .findById(id)
                .map(el -> {
                    repository.delete(el);
                    return el;
                });
    }

    /*
     * gets the generic class type at runtime.
     */
    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
