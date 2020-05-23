package km.exam9.forum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User implements UserDetails {
    @MongoId
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    @Builder.Default
    private String image = "no-image.jpg";
    @NotBlank
    private String login;
    @NotNull
    @Email(message = "example@example.com")
    private String email;
    @NotBlank @Min(4)
    private String password;
    @Builder.Default
    private boolean enabled = true;
    @Builder.Default
    private String role = "USER";
    private int comments;

//    public static User make() {
//        return User.builder()
//                .login(Generator.makeName().toLowerCase())
//                .email(Generator.makeEmail())
//                .password(new BCryptPasswordEncoder().encode(Generator.makePassword()))
//                .build();
//    }

    public void plusComment() {
        this.comments++;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
