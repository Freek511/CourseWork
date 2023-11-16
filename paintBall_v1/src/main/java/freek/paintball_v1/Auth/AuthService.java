package freek.paintball_v1.Auth;

import freek.paintball_v1.Config.JwtService;
import freek.paintball_v1.Entity.User;
import freek.paintball_v1.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepo.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt)
                .build();
    }
}
