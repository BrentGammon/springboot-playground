package uk.co.brentgammon.webservice.src.api.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path="/", produces = "application/json")
    public String home(){
        return "Hello World!";
    }


    @RequestMapping(path="/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addUser(@Valid @RequestBody UserRequest request) {
        return userService.addUser(request);

    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/find")
    public @ResponseBody
    Optional<User> getUser(@RequestParam String id) { return userService.getUserById(Long.valueOf(id)); } //done

}
