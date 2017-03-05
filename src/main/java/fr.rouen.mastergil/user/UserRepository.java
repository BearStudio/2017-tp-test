package fr.rouen.mastergil.user;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by rudy on 04/03/17.
 */
public interface UserRepository{

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneById(Long userId);

    void delete(User t);

    void save(User user);

    List<User> findAll();
}
