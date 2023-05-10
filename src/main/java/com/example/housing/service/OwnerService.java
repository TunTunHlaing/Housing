package com.example.housing.service;

import com.example.housing.entity.Owner;
import com.example.housing.Exception.DataNotFoundException;
import com.example.housing.dto.OwnerDto;
import com.example.housing.repo.OwnerRepo;
import com.example.housing.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepo repo;
    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private PasswordEncoder encoder;

    public OwnerDto create(OwnerDto dto) {
        repo.save(toEntity(dto));
        return dto;
    }

    private Owner toEntity(OwnerDto dto){
        var owenr = new Owner(
                dto.getOwnerName(),
                dto.getEmail(),
                encoder.encode(dto.getPassword()),
                dto.getCreatedDate(),
                dto.getUpdatedDate()
        );
        var role = roleRepo.findRoleByRoleName("OWNER").orElseThrow(() -> new DataNotFoundException("There is no data."));
        owenr.addRoles(role);
        return owenr;
    }

    private OwnerDto toDto(Owner owner){
        return new OwnerDto(
                owner.getOwnerName(),
                owner.getEmail(),
                encoder.encode(owner.getPassword()),
                owner.getCreatedDate(),
                owner.getUpdatedDate()
        );
    }

    public List<OwnerDto> findAllOwner() {
       return repo.findAll().stream().map(o -> toDto(o)).collect(Collectors.toList());
    }
}
