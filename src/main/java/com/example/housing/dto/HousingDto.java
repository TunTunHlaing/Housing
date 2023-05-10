package com.example.housing.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HousingDto {

    private Long id;

    @JsonProperty(value = "housing_name")
    @NotNull(message = "Please enter housing name.")
    private String housingName;

    @NotNull(message = "Please enter address.")
    private String address;

    @NotNull(message = "Please enter number of floors.")
    @JsonProperty("number_of_floors")
    private Integer numberOfFloors;

    @NotNull(message = "Please enter number of floors.")
    @JsonProperty("number_of_master_room")
    private Integer numberOfMasterRoom;

    @NotNull(message = "Please enter number of floors.")
    @JsonProperty("number_of_single_room")
    private Integer numberOfSingleRoom;

    @NotNull(message = "Please enter number of floors.")
    @JsonProperty("amount")
    private Double amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("created_date")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("updated_date")
    private LocalDate updatedDate;

    public HousingDto(String housingName, String address, Integer numberOfFloors, Integer numberOfMasterRoom, Integer numberOfSingleRoom, Double amount, LocalDate createdDate, LocalDate updatedDate) {
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
