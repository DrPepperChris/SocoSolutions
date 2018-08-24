package org.soco.test;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.soco.persistence.dao.UserRepository;
import org.soco.persistence.dao.VerificationTokenRepository;
import org.soco.persistence.model.User;
import org.soco.persistence.model.VerificationToken;
import org.soco.spring.ServiceConfig;
import org.soco.spring.TestDbConfig;
import org.soco.spring.TestIntegrationConfig;
import org.soco.validation.EmailExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDbConfig.class, ServiceConfig.class, TestIntegrationConfig.class })
@Transactional
public class UserIntegrationTest {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Long tokenId;
    private Long userId;

    //

    @Before
    public void givenUserAndVerificationToken() throws EmailExistsException {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("SecretPassword");
        user.setFirstName("First");
        user.setLastName("Last");
        entityManager.persist(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        entityManager.persist(verificationToken);

        entityManager.flush();
        entityManager.clear();

        tokenId = verificationToken.getId();
        userId = user.getId();
    }

    @After
    public void flushAfter() {
        entityManager.flush();
        entityManager.clear();
    }

    //

    @Test
    public void whenContextLoad_thenCorrect() {
        assertTrue(userRepository.count() > 0);
        assertTrue(tokenRepository.count() > 0);
    }

    // @Test(expected = Exception.class)
    @Test
    @Ignore("needs to go through the service and get transactional semantics")
    public void whenRemovingUser_thenFkViolationException() {
        userRepository.deleteById(userId);
    }

    @Test
    public void whenRemovingTokenThenUser_thenCorrect() {
        tokenRepository.deleteById(tokenId);
        userRepository.deleteById(userId);
    }

}
