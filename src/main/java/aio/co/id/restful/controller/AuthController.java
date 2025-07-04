package aio.co.id.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aio.co.id.restful.model.LoginUserRequest;
import aio.co.id.restful.model.TokenRespone;
import aio.co.id.restful.model.WebResponse;
import aio.co.id.restful.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
        path = "/api/auth/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )

    public WebResponse<TokenRespone> login(@RequestBody LoginUserRequest request) throws Exception {

        TokenRespone tokenRespone = authService.login(request);
        return WebResponse.<TokenRespone>builder()
                .data(tokenRespone)
                .build();
    }

}
