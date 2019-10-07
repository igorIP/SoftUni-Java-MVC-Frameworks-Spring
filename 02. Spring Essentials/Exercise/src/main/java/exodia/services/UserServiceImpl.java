package exodia.services;

import exodia.domain.api.Viewable;
import exodia.domain.entities.User;
import exodia.domain.models.binding.UserLoginBindingModel;
import exodia.domain.models.binding.UserRegisterBindingModel;
import exodia.domain.models.view.UserLoggedViewModel;
import exodia.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl extends BaseService<User, UUID, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository,
                           Validator validator,
                           ModelMapper mapper) {
        super(repository, validator, mapper);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean register(UserRegisterBindingModel bindingModel) {
        if (!(bindingModel.getPassword().equals(bindingModel.getConfirmPassword()))) {
            return false;
        }
        if (!validator.validate(bindingModel).isEmpty()) {
            return false;
        }
        if (repository.countAllByUsernameEquals(bindingModel.getUsername()) > 0) {
            return false;
        }
        if (repository.countAllByEmailEquals(bindingModel.getEmail()) > 0) {
            return false;
        }
        return create(bindingModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserLoggedViewModel> login(UserLoginBindingModel bindingModel) {
        //check if bindable obj is not null and valid
        if (bindingModel == null || !validator.validate(bindingModel).isEmpty()) {
            return Optional.empty();
        }
        //check if bindable obj username is in the db
        //if yes take the entity and compare pass
        //if ok map entity to viewable
        return repository
                .findUserByUsername(bindingModel.getUsername())
                .filter(user -> user.getPassword().equals(bindingModel.getPassword()))
                .map(user -> mapper.map(user, UserLoggedViewModel.class));
    }

    @Override
    public <V extends Viewable<User>>
    Optional<V> findById(UUID id, Class<V> viewModelClass) {
        return Optional.empty();
    }
}
