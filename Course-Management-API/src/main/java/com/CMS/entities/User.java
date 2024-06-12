package com.CMS.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private float progress=0.0F;


    @OneToMany(mappedBy = "teacher")
    @JsonIgnoreProperties("teacher") // Prevent serialization of the teacher field in courses
    private Set<Course> courses;

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private Set<Progress> progressSet = new HashSet<>();*/
}
