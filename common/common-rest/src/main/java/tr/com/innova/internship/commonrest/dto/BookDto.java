package tr.com.innova.internship.commonrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private String author;
    private Integer year;
    private String publisher;
    private String takenBy;
    private Date dueDate;
}
