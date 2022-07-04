package tr.com.innova.internship.bookservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
@Data
public class Book {
    @Id
    private int ID;

    @Field
    private String name;

    @Field
    private String author;

    @Field
    private int year;

    @Field
    private String Publisher;

    @Field("taken_by")
    private int takenBy;

    @Field("due_date")
    private Date dueDate;
}
