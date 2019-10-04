package labdemo.service;

import labdemo.domain.enities.Identifiable;
import labdemo.domain.models.binding.Bindable;
import labdemo.domain.models.view.Viewable;

import java.util.List;
import java.util.Optional;

//interface for entities
//(User, String)
//generic <E extends Identifiable<I> -> Classes that implement Identifiable(all entities classes)
//generic I -> class of the ID, always is UUID
public interface Service<E extends Identifiable<I>, I> {

    /*
    Method does this:
    -create entity, in type Bindable+, return obj Viewable+
    -map:
    -IN ( Bindable model -> ViewModel.class)
    -Out  View model
     */
    <B extends Bindable<E>, V extends Viewable<E>> Optional<V> createAndGet(B bindingModel, Class<V> viewModelClass);

    <V extends Viewable<E>> Optional<V> findById(I id, Class<V> viewModelClass);

    <V extends Viewable<E>> List<V> findAll(Class<V> viewModelClass);
}
