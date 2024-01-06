package com.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;


    @Column(name = "email", unique = true)

    private String email;    //no duplicates req

    @Column(name = "password")
    private String password;

    //all details req. first and last name

    private String firstName;

    private String lastName;

    private String location;

    @Column(name = "mobile", unique = true)
    private String mobile;

    private int age;

    private String role="user";

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public User(String email, String password, String firstName, String lastName, String location, String mobile, int age) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.mobile = mobile;
        this.age = age;

    }

    public User(String firstName, String lastName, String email, String mobile, String location, int age, String password,String role) {
    }



}
