package ustavillavicencio.edu.co.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ustavillavicencio.edu.co.bookstore.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;

    private Long expiresIn;

    private UserRole role;
}
