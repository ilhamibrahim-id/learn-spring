package aio.co.id.restful.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import aio.co.id.restful.entity.User;
import aio.co.id.restful.model.RegisterUserRequest;
import aio.co.id.restful.repository.UserRepository;
import aio.co.id.restful.security.BCrypt;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;
    
    @Transactional
    public void register(RegisterUserRequest request) {
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        if (constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if(userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());
        userRepository.save(user);
    }
}
