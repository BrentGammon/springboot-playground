package uk.co.brentgammon.webservice.api.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.co.brentgammon.webservice.src.api.User.User;
import uk.co.brentgammon.webservice.src.api.User.UserController;
import uk.co.brentgammon.webservice.src.api.User.UserRepository;
import uk.co.brentgammon.webservice.src.api.User.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UserIntegrationTest {


    private UserRepository userRepository = mock(UserRepository.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Before
    public void setup() {
        System.out.println("hellow");


    }


    @Test
    public void itShouldReturnHelloWorldOnRoot() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void itShouldReturnAllUsers() throws Exception {
//        UserService userService = new UserService(userRepository);
//
        User user1 = new User("testUser1", "user1@email.com");
        User user2 = new User("testUser2", "user2@email.com");
        List<User> users = Arrays.asList(user1,user2);

        when(userRepository.findAll()).thenReturn(users);


        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/user/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());


    }
}
