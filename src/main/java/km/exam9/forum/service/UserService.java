package km.exam9.forum.service;

import km.exam9.forum.DTO.UserDTO;
import km.exam9.forum.exception.UserAlreadyRegisteredException;
import km.exam9.forum.exception.UserNotFoundException;
import km.exam9.forum.forms.UserRegisterForm;
import km.exam9.forum.model.User;
import km.exam9.forum.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public boolean checkUser(UserRegisterForm form) {
        return userRepository.existsByLoginAndEmail(form.getLogin(), form.getEmail());
    }

    public void register(UserRegisterForm form) {
        if(checkUser(form)) {
            throw  new UserAlreadyRegisteredException();
        }
        var user = User.builder()
                .email(form.getEmail())
                .login(form.getLogin())
                .password(encoder.encode(form.getPassword()))
                .build();
        userRepository.save(user);
    }

    public UserDTO getByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return UserDTO.from(user);
    }
    public User getUserByEmail(String email) {
        var user = userRepository.findByEmail(email).get();
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
