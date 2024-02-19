package org.sergiorebelo.piupiu.usermanagement.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.sergiorebelo.piupiu.usermanagement.entity.User;
import org.sergiorebelo.piupiu.usermanagement.entity.UserProfile;

@ApplicationScoped
public class UserProfileRepository {

    @Inject
    EntityManager entityManager;


    @Transactional
    public UserProfile findByUserId(Long userId) {
        return entityManager.createQuery("SELECT up FROM UserProfile up WHERE up.user.id = :userId", UserProfile.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Transactional
    public UserProfile findByUser(User user) {
        return entityManager.createQuery("SELECT up FROM UserProfile up WHERE up.user = :user", UserProfile.class)
                .setParameter("user", user)
                .getSingleResult();
    }

    @Transactional
    public void createUserProfile(UserProfile profile) {

        entityManager.persist(profile);
    }
}
