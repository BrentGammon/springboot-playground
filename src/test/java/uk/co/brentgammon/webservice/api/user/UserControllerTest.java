package uk.co.brentgammon.webservice.api.user;

import org.junit.Test;
import uk.co.brentgammon.webservice.src.api.User.User;
import uk.co.brentgammon.webservice.src.api.User.UserController;
import uk.co.brentgammon.webservice.src.api.User.UserService;

import java.util.Arrays;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    private static UserService userService = mock(UserService.class);

    @Test
    public void itShouldReturnHelloWorldByTheRouteEndpoint() {
        UserController userController = new UserController(userService);
        String response = userController.home();

        assertThat(response).isEqualTo("Hello World!");


    }


    @Test
    public void itShouldReturnAllUsers() {
        UserController userController = new UserController(userService);
        Iterable<User> response = Arrays.asList(new User("Bob", "bob@email.com"), new User("Bill", "bill@email.com"));
        when(userController.getAllUsers()).thenReturn(response);
        Iterable<User> actualResponse = userController.getAllUsers();

        assertThat(response).isEqualTo(actualResponse);
    }

    @Test
    public void itShouldReturnUserById() {
        UserController userController = new UserController(userService);
        User user = new User("bob", "some-email");

        when(userController.getUser("0")).thenReturn(Optional.of(user));
        Optional<User> response = userController.getUser("0");

        assertThat(response).isEqualTo(Optional.of(user));
    }

    @Test
    public void itShouldReturnNullIfNoUserIsFound() {
        UserController userController = new UserController(userService);

        when(userController.getUser("0")).thenReturn(null);
        Optional<User> response = userController.getUser("0");

        assertThat(response).isEqualTo(null);


    }
}
