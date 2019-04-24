package uk.co.brentgammon.webservice.src.api.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User addNewUser(String name, String email) {

//        User user = User.builder()
//                .email(email)
//                .name(name)
//                .build();

        User user = new User(name,email);

         return userRepository.save(user);
        //return "Saved";
    }

    public String addUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();


        userRepository.save(user);
        return "Saved";
    }
}
