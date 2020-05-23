package km.exam9.forum.DTO;

import km.exam9.forum.model.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@Builder
public class CommentDTO {
    
    public static CommentDTO from(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return CommentDTO.builder()
                .id(comment.getId())
                .time(comment.getTime().format(formatter))
                .user(UserDTO.from(comment.getUser()))
                .comment(comment.getComment())
                .build();
    }

    private String id;
    private String time;
    private UserDTO user;
    private String comment;

}
