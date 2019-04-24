package uk.co.brentgammon.webservice.api.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.brentgammon.webservice.src.api.User.User;
import uk.co.brentgammon.webservice.src.api.User.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("integrationtest")
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void returnUserWhenIdMatches() {
        //given
        User user = new User("some-name", "some-email");
        entityManager.persist(user);
        entityManager.flush();

        //when
        Optional<User> responseUser = userRepository.findById(1L);


        //then
        assertThat(user).isEqualTo(responseUser.get());
    }

}
