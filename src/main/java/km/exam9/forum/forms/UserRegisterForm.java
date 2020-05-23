package km.exam9.forum.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterForm {
    @NotBlank
    @Email
    private String email = "";
    @NotBlank
    @Size(min = 6, message = "length must be > 6")
    private String password = "";

    @Size(min=4, max=24, message = "length must be >= 4 and <= 24")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "should contain only letters")
    private String login = "";

}
