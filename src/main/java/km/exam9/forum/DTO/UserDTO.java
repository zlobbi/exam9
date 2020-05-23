package km.exam9.forum.DTO;

import km.exam9.forum.model.User;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserDTO {
    private String id;
    private String login;
    private String image;
    private String email;
    private int comments;

    public static UserDTO from(User user) {
        return builder().id(user.getId())
                .email(user.getEmail())
                .image(user.getImage())
                .login(user.getLogin())
                .comments(user.getComments())
                .build();

    }

}
