package aio.co.id.restful.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import aio.co.id.restful.entity.User;
import aio.co.id.restful.model.LoginUserRequest;
import aio.co.id.restful.model.TokenRespone;
import aio.co.id.restful.repository.UserRepository;
import aio.co.id.restful.security.BCrypt;
import jakarta.transaction.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenRespone login(LoginUserRequest request) throws Exception {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername()).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30days()); 
            userRepository.save(user);

            return TokenRespone.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    private long next30days() {
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }
}
