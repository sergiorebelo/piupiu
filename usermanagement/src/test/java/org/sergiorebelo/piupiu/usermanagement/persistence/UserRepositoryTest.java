package org.sergiorebelo.piupiu.usermanagement.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.sergiorebelo.piupiu.usermanagement.business.UserService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;




public class UserRepositoryTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    UserRepository userRepository;


    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_Empty() {
        // Mock EntityManager and CriteriaBuilder
        EntityManager entityManager = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        // Prepare data
        List<User> userList = List.of();
        when(typedQuery.getResultList()).thenReturn(userList);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        List<User> result = userRepository.findAll();

        // Verify
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testFindAll_One() {
        // Mock EntityManager and CriteriaBuilder
        EntityManager entityManager = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        // Prepare data
        List<User> userList = List.of(new User());
        when(typedQuery.getResultList()).thenReturn(userList);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        List<User> result = userRepository.findAll();

        // Verify
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindAll_More() {
        // Mock EntityManager and CriteriaBuilder
        EntityManager entityManager = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        // Prepare data
        List<User> userList = Arrays.asList(new User(), new User(), new User());
        when(typedQuery.getResultList()).thenReturn(userList);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        List<User> result = userRepository.findAll();

        // Verify
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void testGetMostRecentUser() {
        // Mock EntityManager, CriteriaBuilder, CriteriaQuery, Root, and TypedQuery
        EntityManager entityManager = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.orderBy(anyList())).thenReturn(criteriaQuery);

        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        // Prepare data
        User user1 = new User("john", "pass123");

        when(typedQuery.setMaxResults(1)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(user1);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        Optional<User> result = userRepository.getMostRecentUser();

        // Verify
        assertTrue(result.isPresent());
        assertEquals(user1.getUsername(), result.get().getUsername());
    }

    @Test
    void testGetMostRecentUser_Empty() {
        // Mock EntityManager, CriteriaBuilder, CriteriaQuery, Root, and TypedQuery
        EntityManager entityManager = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.orderBy(anyList())).thenReturn(criteriaQuery);

        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        // Prepare data

        when(typedQuery.setMaxResults(1)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(null);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        Optional<User> result = userRepository.getMostRecentUser();

        // Verify
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUserById() {
        // Mock EntityManager
        EntityManager entityManager = mock(EntityManager.class);

        // Prepare data
        User user = new User();
        when(entityManager.find(User.class, 1L)).thenReturn(user);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        Optional<User> result = userRepository.getUserById(1L);

        // Verify
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }


    @Test
    void testGetUserById_NotFound() {
        // Mock EntityManager
        EntityManager entityManager = mock(EntityManager.class);

        // Prepare data
        when(entityManager.find(User.class, 1L)).thenReturn(null);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        Optional<User> result = userRepository.getUserById(1L);

        // Verify
        assertTrue(result.isEmpty());
    }


    @Test
    void testGetUserByUsername() {

        // Mock EntityManager, CriteriaBuilder, CriteriaQuery, and Root
        EntityManager entityManager = mock(EntityManager.class);

        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        // Prepare data
        User user = new User();

        when(typedQuery.setMaxResults(1)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(user);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        Optional<User> result = userRepository.getUserByUsername("username");

        // Verify
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void testGetUserByUsername_NotFound() {

        // Mock EntityManager, CriteriaBuilder, CriteriaQuery, and Root
        EntityManager entityManager = mock(EntityManager.class);

        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        // Prepare data


        when(typedQuery.setMaxResults(1)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(null);

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        Optional<User> result = userRepository.getUserByUsername("username");

        // Verify
        assertTrue(result.isEmpty());
    }





    @Test
    void createUser() {
        // Mock EntityManager
        EntityManager entityManager = mock(EntityManager.class);

        // Prepare data
        User user = new User();

        // Inject EntityManager into UserRepository
        UserRepository userRepository = new UserRepository();
        userRepository.entityManager = entityManager;

        // Execute
        userRepository.createUser(user);

        // Verify
        verify(entityManager).persist(user);
    }



}
