package tr.com.innova.internship.userservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
@Data
public class User {
    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String surname;

    @Indexed(unique = true)
    private String email;

    @Field
    private Integer year;

    @Field
    private String password;
}
