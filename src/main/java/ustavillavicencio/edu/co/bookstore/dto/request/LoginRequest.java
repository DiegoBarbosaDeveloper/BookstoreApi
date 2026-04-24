package ustavillavicencio.edu.co.bookstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String username;
    
    @Email
    private String email;
    
    @NotBlank
    @Min(value = 8, message = " Password must be at least 8 characters long")
    private String password;
    
}
