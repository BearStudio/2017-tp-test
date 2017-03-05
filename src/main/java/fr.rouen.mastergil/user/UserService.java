package fr.rouen.mastergil.user;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by rudy on 04/03/17.
 */
public class UserService {

    private MailService mailService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthorityRepository authorityRepository;

    private MyLogger log;



    public User createUserInformation(String login, String password, String firstName, String lastName, String email,
                                      String langKey) {

        User newUser = new User();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }



    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
                .map(user -> {
                    // activate given user for the registration key.
                    user.setActivated(true);
                    user.setActivationKey(null);
                    userRepository.save(user);
                    log.debug("Activated user: {}", user);
                    return user;
                });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);

        return userRepository.findOneByResetKey(key)
                .filter(user -> {
                    ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
                    return user.getResetDate().isAfter(oneDayAgo);
                })
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    userRepository.save(user);
                    return user;
                });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
                .filter(User::isActivated)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(ZonedDateTime.now());
                    userRepository.save(user);
                    return user;
                });
    }

    public void checkUserStatus() {
        userRepository.findAll().stream()
                .forEach( user ->  {
                   logIsActivated(user);
                    logResetDate(user);
                });


    }



    private void logIsActivated(User user) {
        if (user.isActivated()) {
            log.debug("user {} is activated", user);
        } else {
            log.debug("user {} is not activated", user);
        }

    }

    private void logResetDate(User user) {
        ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
        if (user.getResetDate() != null) {
            if ( ! user.isActivated() && user.getResetDate().isBefore(oneDayAgo)) {
                log.debug("reset date is past for user {}", user);
            } else {
                log.debug("reset date for {}", user);
            }

        }

    }



}
