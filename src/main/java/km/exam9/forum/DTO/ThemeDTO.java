package km.exam9.forum.DTO;

import km.exam9.forum.model.Theme;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@Builder
public class ThemeDTO {

    public static ThemeDTO from(Theme theme) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return ThemeDTO.builder()
                .id(theme.getId())
                .theme(theme.getTheme())
                .description(theme.getDescription())
                .time(theme.getTime().format(formatter))
                .userImage(theme.getUser().getImage())
                .userName(theme.getUser().getLogin())
                .comments(theme.getComments())
                .build();
    }

    private String id;
    private String theme;
    private String description;
    private String time;
    private String userImage;
    private String userName;
    private int comments;
}
