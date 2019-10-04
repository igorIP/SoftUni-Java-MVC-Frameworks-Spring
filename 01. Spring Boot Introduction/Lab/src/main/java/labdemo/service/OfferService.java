package labdemo.service;

import labdemo.domain.enities.Offer;
import labdemo.domain.models.binding.Bindable;
import labdemo.domain.models.binding.OfferFindBindingModel;
import labdemo.domain.models.view.Viewable;

import java.util.Optional;
import java.util.UUID;

public interface OfferService extends Service<Offer, UUID> {

    <B extends Bindable<Offer>> boolean registerOffer(B bindingModel);

    <V extends Viewable<Offer>> Optional<V> findOffer(OfferFindBindingModel bindingModel, Class<V> viewModelClass);
}
