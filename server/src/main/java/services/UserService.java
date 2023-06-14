package services;

import repositories.UserRepository;
import exceptions.*;
import models.User;
import utils.PasswordEncoder;

/** Класс, осуществляющий пользовательскую бизнес-логику.
 * Вызывается View, валидирует переданные параметры и кидает исключения. */
public class UserService {
    private final UserRepository repository = UserRepository.getInstance();

    private static final UserService instance = new UserService();
    private UserService() {}
    public static UserService getInstance() {
        return instance;
    }

    public void register(User user) throws ServerException {
        if (repository.findByUsername(user.getUsername()) != null) {
            throw new ValidationFailed("User with this username already exists.");
        }
        repository.create(user);
    }

    public User login(User u) throws ServerException {
        User user = repository.findByUsername(u.getUsername());
        if (user == null) {
            throw new NotFound("User with this username doesn't exist.");
        }
        if (!PasswordEncoder.encode(u.getPassword()).equals(user.getPassword())) {
            throw new ValidationFailed("Wrong password.");
        }
        return user;
    }
}
