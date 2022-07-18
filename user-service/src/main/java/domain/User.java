package domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

    @Document
    @Data
    public class User {
        public void setID(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLastName(String lastName) {this.lastName = lastName;}

        public void setEmail(String email) {
            this.email = email;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public Integer getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getYear() {
            return year;
        }


        @Id
        private Integer id;

        @Field
        private String name;

        @Field
        private String lastName;
        @Field
        private String email;

        @Field
        private int year;
        
}
