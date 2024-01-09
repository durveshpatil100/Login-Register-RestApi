package com.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @Column(name= "Role_ID",nullable = false)
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int roleId;

    @Column(name = "Role_Name", nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    Set<User> user = new HashSet<>();
}
