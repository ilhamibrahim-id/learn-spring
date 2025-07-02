package aio.co.id.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aio.co.id.restful.entity.User;
import aio.co.id.restful.model.RegisterUserRequest;
import aio.co.id.restful.model.UserRespone;
import aio.co.id.restful.model.WebResponse;
import aio.co.id.restful.service.UserService;

@RestController
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping(
            path = "/api/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
        )
        public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
            userService.register(request);
            return WebResponse.<String>builder()
                    .data("OK")
                    .build();
        }

        @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
        )
        public WebResponse<UserRespone> get(User user) {
            UserRespone userRespone = userService.get(user);
            return WebResponse.<UserRespone>builder().data(userRespone).build();
        }

        @PostMapping(
            path = "/api/users_by-username",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
        )
        public WebResponse<User> getByUsername(User user) {
            User userDetail = userService.getByUsername(user);
            return WebResponse.<User>builder().data(userDetail).build();
        }

}
