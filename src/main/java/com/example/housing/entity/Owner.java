package com.example.housing.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Owner implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;


    @NotNull(message = "Please Enter Owner  Name.")
    private String ownerName;

    @NotNull(message = "Please Enter Email.")
    @Column(unique = true)
    private String email;


    @NotNull(message = "Please Enter Password.") private String password;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Housing> housingList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public void addRoles(Role role){
        role.getOwners().add(this);
        roles.add(role);
    }

    public void addHousing(Housing housing){
        housing.setOwnerId(this.ownerId);
        housingList.add(housing);
    }

    public Owner() {
        super();
    }

    public Owner(@NotNull(message = "Please Enter Owner  Name.") String ownerName,
                 @NotNull(message = "Please Enter Email.") String email,
                 @NotNull(message = "Please Enter Password.") String password, LocalDate createdDate, LocalDate updatedDate) {
        super();
        this.ownerName = ownerName;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;

    }



}

