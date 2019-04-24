package uk.co.brentgammon.webservice.api.user;

import org.junit.Test;
import spock.lang.Specification;
import uk.co.brentgammon.webservice.src.api.User.User;
import uk.co.brentgammon.webservice.src.api.User.UserRepository;
import uk.co.brentgammon.webservice.src.api.User.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class UserServiceTest{
    private UserRepository userRepository = mock(UserRepository.class);

    @Test
    public void itShouldAllUsers() {
        UserService userService = new UserService(userRepository);

        Iterable<User> users = Arrays.asList(new User("bob", "some-email"), new User("bill", "some-other-email"));
        when(userService.getAllUsers())
                .thenReturn(users);

        Iterable<User> response = userService.getAllUsers();

        assertThat(response).isEqualTo(users);
    }

    @Test
    public void itShouldReturnUserIfOneMatched() {
        UserService userService = new UserService(userRepository);

        User user = new User("bob", "some-email");
        when(userService.getUserById(0L))
                .thenReturn(Optional.of(user));

        Optional<User> response = userService.getUserById(0L);

        assertThat(response).isEqualTo(Optional.of(user));
    }

    @Test
    public void itShouldReturnNullIfNoneMatched() {
        UserService userService = new UserService(userRepository);

        when(userService.getUserById(0L))
                .thenReturn(null);

        Optional<User> response = userService.getUserById(0L);

        assertThat(response).isEqualTo(null);
    }

    @Test
    public void itShouldReturnMessageThatUserIsSaved() {
        UserService userService = new UserService(userRepository);

        when(userService.addNewUser("bob", "some-email"))
                .thenReturn(User.builder()
                        .email("some-email")
                        .name("bob")
                        .build()
                );


        User response = userService.addNewUser("bob", "some-email");

        assertThat(response).isEqualTo(User.builder().email("some-email").name("bob").build());

    }

}
