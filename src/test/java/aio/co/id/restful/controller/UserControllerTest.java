package aio.co.id.restful.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import aio.co.id.restful.entity.User;
import aio.co.id.restful.model.RegisterUserRequest;
import aio.co.id.restful.model.WebResponse;
import aio.co.id.restful.repository.UserRepository;
import aio.co.id.restful.security.BCrypt;

@AutoConfigureMockMvc
@org.springframework.boot.test.context.SpringBootTest

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("testuser");
        request.setPassword("password123");
        request.setName("Test User");
        
        mockMvc.perform(
                post("/api/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("OK", response.getData());
        });
    }

    @Test
    void testRegisterBadRequest() throws Exception {

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("");
        request.setPassword("");
        request.setName("");
        
        mockMvc.perform(
                post("/api/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getError());
        });
    }

    @Test
    void testRegisterDuplicate() throws Exception {
        
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setName("test");
        userRepository.save(user);

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("test");
        request.setPassword("rahasia");
        request.setName("test");
        
        mockMvc.perform(
                post("/api/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getError());
        });
    }
}
