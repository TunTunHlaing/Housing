package com.example.housing.service;

import com.example.housing.dto.HousingDto;
import com.example.housing.repo.HousingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private HousingRepo repo;
    @Autowired
    private HousingService service;


    public Page<HousingDto> findAllHousing(int page, int pageSize) {
        var pageable = PageRequest.of(page, pageSize);
        var result = repo.findAll(pageable).stream().map(h -> service.toDto(h)).collect(Collectors.toList());
        Page<HousingDto> main = new PageImpl<>(result);
        return main;
    }

}
