package ustavillavicencio.edu.co.bookstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ustavillavicencio.edu.co.bookstore.dto.request.LoginRequest;
import ustavillavicencio.edu.co.bookstore.dto.request.RegisterRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.AuthResponse;

public interface AuthService extends UserDetailsService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
