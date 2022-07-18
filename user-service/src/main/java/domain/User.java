package domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


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
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMatchingPassword() {
            return matchingPassword;
        }

        public void setMatchingPassword(String matchingPassword) {
            this.matchingPassword = matchingPassword;
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

        @Field
        private String password;

        @Field
        private String matchingPassword;

        
}
