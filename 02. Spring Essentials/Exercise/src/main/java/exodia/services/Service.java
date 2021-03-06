package exodia.services;


import exodia.domain.api.Bindable;
import exodia.domain.api.Identifiable;
import exodia.domain.api.Viewable;

import javax.swing.text.View;
import java.util.List;
import java.util.Optional;

/**
 * Service interface, meant for extension by specific services
 *
 * @param <E> Entity class
 * @param <I> ID class
 */
public interface Service<E extends Identifiable<I>, I> {

    <B extends Bindable<E>> boolean create(B bindingModel);

    <B extends Bindable<E>, V extends Viewable<E>> Optional<V> createAndGet(B bindingModel, Class<V> viewModelClass);

    <V extends Viewable<E>> Optional<V> findById(I id, Class<V> viewModelClass);

    <V extends Viewable<E>> List<V> findAll(Class<V> viewModelClass);

    boolean deleteById(I id);

    <V extends Viewable<E>> Optional<V> deleteByIdAndGet(I id, Class<V> viewModelClass);
}
