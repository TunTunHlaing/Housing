package com.example.housing.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String housingName;

    private String address;

    private Integer numberOfFloors;

    private Integer numberOfMasterRoom;


    private Integer numberOfSingleRoom;

    private Double amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;

    private Long ownerId;

    public Housing() {
    }

    public Housing(String housingName, String address, Integer numberOfFloors, Integer numberOfMasterRoom, Integer numberOfSingleRoom, Double amount, LocalDate createdDate, LocalDate updatedDate) {
        this.housingName = housingName;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.numberOfMasterRoom = numberOfMasterRoom;
        this.numberOfSingleRoom = numberOfSingleRoom;
        this.amount = amount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
