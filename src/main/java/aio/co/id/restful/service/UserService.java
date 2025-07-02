package aio.co.id.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import aio.co.id.restful.entity.User;
import aio.co.id.restful.model.RegisterUserRequest;
import aio.co.id.restful.model.UserRespone;
import aio.co.id.restful.repository.UserRepository;
import aio.co.id.restful.security.BCrypt;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;
    
    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        if(userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());
        userRepository.save(user);
    }
    public UserRespone get(User user) {
        return UserRespone.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
    public User getByUsername(User user) {
        return userRepository.findById(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
