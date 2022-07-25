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
public class RentDto {
    private String bookId;
    private String takenBy;
    private Date dueDate;
}
