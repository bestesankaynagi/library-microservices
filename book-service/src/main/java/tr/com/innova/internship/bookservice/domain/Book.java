package tr.com.innova.internship.bookservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Optional;

@Document
@Data
public class Book {
    public void setID(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public void setTakenBy(int takenBy) {
        this.takenBy = takenBy;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return Publisher;
    }

    public int getTakenBy() {
        return takenBy;
    }

    public Date getDueDate() {
        return dueDate;
    }

    @Id
    private Integer id;

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
