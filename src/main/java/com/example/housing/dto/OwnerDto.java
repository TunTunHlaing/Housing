package com.example.housing.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class OwnerDto {

    private Long ownerId;

    @NotNull(message = "Please Enter Owner  Name.")
    private String ownerName;

    @NotNull(message = "Please Enter Email.")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Please Enter Password")
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;

    public OwnerDto(String ownerName, String email, String password, LocalDate createdDate, LocalDate updatedDate) {
        this.ownerName = ownerName;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
