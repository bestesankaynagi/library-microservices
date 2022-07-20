package tr.com.innova.internship.bookservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
@Data
public class Book {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    @Field
    private String author;

    @Field
    private int year;

    @Field
    private String publisher;

    @Field("taken_by")
    private String takenBy;

    @Field("due_date")
    private Date dueDate;
}
