package km.exam9.forum.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ThemeForm {
    @NotBlank
    @Size(min = 6, message = "Theme length must be > 6")
    private String theme = "";

    @Size(min=10, message = "Description length must be greater than 10")
    private String description  = "";
}
