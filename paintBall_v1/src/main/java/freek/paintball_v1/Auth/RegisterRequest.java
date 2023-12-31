package freek.paintball_v1.Auth;

import freek.paintball_v1.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
    private String login;
    private String email;
    private String password;
    private Role role;
}
