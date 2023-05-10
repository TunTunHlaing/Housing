package com.example.housing.controller;

import com.example.housing.dto.HousingDto;
import com.example.housing.service.HousingService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("owner")
public class OwnerApi {

    @Autowired
    private HousingService service;

    @PostMapping(value = "{owernId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HousingDto createHousing(@RequestBody @Valid HousingDto housingDto
            , @PathVariable Long owernId, BindingResult result){
      
        return service.createHousing(housingDto, owernId);
    }

    @GetMapping
    public List<HousingDto> ownerHousings(@RequestParam Long id){
        return service.housingsOfOwner(id);
    }

    @GetMapping("search")
    public List<HousingDto> searchHousingPagination(
          @RequestParam  Optional<String> housingName,
          @RequestParam Optional<Integer> numberOfFloors,
          @RequestParam  Optional<Integer> numberOfMasterRooms,
          @RequestParam Optional<Integer> numberOfSingleRooms,
          @RequestParam  Optional<Double> amount,
          @RequestParam Optional<LocalDate> createdDate
    ){
        return service.search(housingName,numberOfFloors,
                numberOfMasterRooms,numberOfSingleRooms,amount,createdDate);
    }

    @GetMapping("all")
    public Page<HousingDto> paginationHousing(
            @RequestParam("id") Long ownerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize){
        return service.paginationHousing(ownerId, page, pageSize);
    }


    @PutMapping("edit")
    public HousingDto updateHousing(@RequestBody HousingDto dto, @RequestParam Long id){
        return service.update(dto, id);
    }

}
