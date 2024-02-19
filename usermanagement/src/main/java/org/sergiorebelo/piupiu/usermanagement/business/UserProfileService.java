package org.sergiorebelo.piupiu.usermanagement.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.sergiorebelo.piupiu.usermanagement.entity.User;
import org.sergiorebelo.piupiu.usermanagement.entity.UserProfile;
import org.sergiorebelo.piupiu.usermanagement.persistence.UserProfileRepository;

@ApplicationScoped
public class UserProfileService {

    @Inject
    UserProfileRepository userProfileRepository;

    // Method to retrieve the default user profile
    protected UserProfile getDefaultUserProfile() {

        return new UserProfile();
    }

    // Method to create a new user profile for a user
    @Transactional
    public void createUserProfile(User user) {

        // empty profile. Set user.
        UserProfile profile = getDefaultUserProfile();
        profile.setUser(user);

        // Persists
        userProfileRepository.createUserProfile(profile);
    }

    // Other methods for managing user profiles
}
