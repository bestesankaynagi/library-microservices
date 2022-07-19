package tr.com.innova.internship.commonrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String surname;
    private String password;
    private String name;
    private String email;
    private Integer year;
}
