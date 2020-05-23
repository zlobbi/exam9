package km.exam9.forum.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDTO {
    private boolean success;
    @JsonAlias("error-codes")
    private List<String> errorCodes;
}
