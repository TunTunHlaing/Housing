package com.example.housing.controller;

import com.example.housing.dto.HousingDto;
import com.example.housing.service.UserService;
import com.example.housing.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserApi {

    @Autowired
    private UserService service;

    @Autowired
    private HousingService housingService;

    @GetMapping()
    public Page<HousingDto> findAllHousing(
    		@RequestParam(defaultValue = "0") int page,
    		@RequestParam(defaultValue = "5") int pageSize){
        return service.findAllHousing(page, pageSize);
    }

    @GetMapping("search")
    public List<HousingDto> searchHousingPagination(
            @RequestParam Optional<String> housingName,
            @RequestParam Optional<Integer> numberOfFloors,
            @RequestParam  Optional<Integer> numberOfMasterRooms,
            @RequestParam Optional<Integer> numberOfSingleRooms,
            @RequestParam  Optional<Double> amount,
            @RequestParam Optional<LocalDate> createdDate
    ){
        return housingService.search(housingName,numberOfFloors,
                numberOfMasterRooms,numberOfSingleRooms,amount,createdDate);
    }
}
