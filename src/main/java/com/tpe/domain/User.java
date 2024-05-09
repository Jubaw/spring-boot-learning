package com.tpe.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 25)
    private String firstName;

    @Column(nullable = false,length = 25)
    private String lastName;

    @Column(nullable = false,length = 25,unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;  //Not giving  length parameter because of different hashing alghorithims give different length of has



    @JoinTable(
            name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)  //Many users having many roles
    private Set<Role> roles  = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Student student;






















}
