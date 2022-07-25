package tr.com.innova.internship.apigateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
public class RevokedToken {
    @Id
    private String id;
    private Date revokedAt;
}
