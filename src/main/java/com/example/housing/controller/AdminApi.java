package com.example.housing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.housing.dto.OwnerDto;
import com.example.housing.service.OwnerService;

@RestController
@RequestMapping("admin")
public class AdminApi {

    @Autowired
    private OwnerService service;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public OwnerDto create(OwnerDto dto, BindingResult result){

        return service.create(dto);
    }

    @GetMapping()
    public List<OwnerDto> findALlOwner(){
        return service.findAllOwner();
    }
}
