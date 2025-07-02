package aio.co.id.restful.model;

import aio.co.id.restful.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactRespone {

    private User user;

    @NotBlank
    @Size(max = 100)
    private String id;

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    private String lastName;

    @NotBlank
    @Size(max = 100)
    private String phone;

    @NotBlank
    @Size(max = 100)
    private String email;

}
