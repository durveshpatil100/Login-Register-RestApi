package com.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;


    @Column(name = "email", unique = true, nullable = false)
    private String email;    //no duplicates req

    @Column(name = "password",nullable = false)
    private String password;

    //all details req. first and last name

    private String firstName;

    private String lastName;

    private String location;

    @Column(name = "mobile", unique = true)
    private String mobile;

    private int age;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Roles> roles = new HashSet<>();

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> collect = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toSet());
        return collect;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
