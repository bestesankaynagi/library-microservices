package tr.com.innova.internship.apigateway.controller;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    private Date createDate=new Date();
    @Id
    private String id;
    private String name;
   /* public Library(){

    }
    public Library(String id, String name) {
        this.id = id;
        this.name = name;
    }*/
}
