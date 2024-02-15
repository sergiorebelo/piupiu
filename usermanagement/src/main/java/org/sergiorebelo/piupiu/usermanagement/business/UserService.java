package org.sergiorebelo.piupiu.usermanagement.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.sergiorebelo.piupiu.usermanagement.entity.User;
import org.sergiorebelo.piupiu.usermanagement.persistence.UserRepository;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;
    @Inject
    UserProfileService userProfileService;

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void createUser(User user) {

        userRepository.createUser(user);

        //create a profile
        userProfileService.createUserProfile(user);
    }

    public Optional<User> getUserById(long id) {

        return userRepository.getUserById(id);
    }

    public Optional<User> getUserByUsername(String username) {

        return userRepository.getUserByUsername(username);
    }
}
