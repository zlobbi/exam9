package km.exam9.forum.model;

import km.exam9.forum.forms.ThemeForm;
import km.exam9.forum.util.Generator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "themes")
public class Theme {
    @MongoId
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    @NotBlank
    private String theme;
    @NotBlank
    private String description;
    @Indexed
    @DBRef
    private User user;
    private LocalDateTime time;
    @Builder.Default
    private int comments = 0;

    public static Theme make(User u) {
        Random r = new Random();
        return builder()
                .theme(Generator.makePassword() + " " + Generator.makeName())
                .description(Generator.makeDescription())
                .user(u)
                .time(LocalDateTime.now().minusDays(r.nextInt(20) + 1))
                .build();
    }
    public void plusComent() {
        this.comments++;
    }

    public static Theme from(ThemeForm theme, User user) {
        return builder()
                .theme(theme.getTheme())
                .description(theme.getDescription())
                .time(LocalDateTime.now())
                .user(user)
                .build();
    }

}
