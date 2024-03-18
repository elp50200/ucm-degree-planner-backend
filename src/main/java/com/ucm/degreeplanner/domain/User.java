package com.ucm.degreeplanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//this is just a placeholder file
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private String studentNumber;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer userID;
    private String username;
    private String password;
    private String emailAddress;

    private String fname;
    private String lname;
    private String role;
    private String catalogYear;


//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<ChangeRequest> ChangeRequests = new ArrayList<>();

}