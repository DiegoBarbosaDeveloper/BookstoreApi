package ustavillavicencio.edu.co.bookstore.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.LoginRequest;
import ustavillavicencio.edu.co.bookstore.dto.request.RegisterRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.AuthResponse;
import ustavillavicencio.edu.co.bookstore.entity.UserEntity;
import ustavillavicencio.edu.co.bookstore.enums.UserRole;
import ustavillavicencio.edu.co.bookstore.respository.UserRepository;
import ustavillavicencio.edu.co.bookstore.security.JwtService;
import ustavillavicencio.edu.co.bookstore.service.AuthService;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.jwt.expiration}")
    private long expirationMs;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(CONFLICT, "Email already in use");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(CONFLICT, "Username already in use");
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setRole(UserRole.USER);

        UserEntity saved = userRepository.save(entity);
        UserDetails userDetails = toUserDetails(saved);
        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, expirationMs, saved.getRole());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .or(() -> userRepository.findByUsername(request.getUsername()))
                .orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(UNAUTHORIZED, "Invalid credentials");
        }

        UserDetails userDetails = toUserDetails(user);
        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, expirationMs, user.getRole());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .or(() -> userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return toUserDetails(user);
    }

    private UserDetails toUserDetails(UserEntity user) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
