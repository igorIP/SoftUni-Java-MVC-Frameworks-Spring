package exodia.services;


import exodia.domain.entities.User;
import exodia.domain.models.binding.UserLoginBindingModel;
import exodia.domain.models.binding.UserRegisterBindingModel;
import exodia.domain.models.view.UserLoggedViewModel;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends Service<User, UUID> {

    boolean register(UserRegisterBindingModel bindingModel);

    Optional<UserLoggedViewModel> login(UserLoginBindingModel bindingModel);
}
