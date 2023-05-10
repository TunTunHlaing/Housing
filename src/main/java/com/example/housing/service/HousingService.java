package com.example.housing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.housing.Exception.DataNotFoundException;
import com.example.housing.dto.HousingDto;
import com.example.housing.entity.Housing;
import com.example.housing.repo.HousingRepo;
import com.example.housing.repo.OwnerRepo;

@Service
public class HousingService {

    @Autowired
    private HousingRepo housingRepo;
    @Autowired
    private OwnerRepo ownerRepo;

   

    @Transactional
    public HousingDto createHousing(HousingDto housingDto, Long owernId) {

        var owner = ownerRepo.findById(owernId).orElseThrow(() -> new DataNotFoundException("There is no data."));
        var housing = toEntity(housingDto);
        owner.addHousing(housing);
        housingRepo.save(housing);
        ownerRepo.save(owner);
        return housingDto;
    }

    private Housing toEntity(HousingDto housingDto) {

        return new Housing(
                housingDto.getHousingName(),
                housingDto.getAddress(),
                housingDto.getNumberOfFloors(),
                housingDto.getNumberOfMasterRoom(),
                housingDto.getNumberOfSingleRoom(),
                housingDto.getAmount(),
                housingDto.getCreatedDate(),
                housingDto.getUpdatedDate()
        );
    }



    HousingDto toDto(Housing housing) {

        return new HousingDto(
                housing.getHousingName(),
                housing.getAddress(),
                housing.getNumberOfFloors(),
                housing.getNumberOfMasterRoom(),
                housing.getNumberOfSingleRoom(),
                housing.getAmount(),
                housing.getCreatedDate(),
                housing.getUpdatedDate()
        );
    }

    public List<HousingDto> housingsOfOwner(Long id) {
        var owner = ownerRepo.findById(id).orElseThrow(() -> new DataNotFoundException("There is no data."));
        return owner.getHousingList().stream().map(h -> toDto(h)).collect(Collectors.toList());
    }

    public List<HousingDto> search(Optional<String> housingName,
                                   Optional<Integer> numberOfFloors,
                                   Optional<Integer> numberOfMasterRooms,
                                   Optional<Integer> numberOfSingleRooms,
                                   Optional<Double> amount,
                                   Optional<LocalDate> createdDate) {

        var housing = housingRepo.findAll(whichHousingName(housingName).and(whichNumberOfFloors(numberOfFloors).and(whichNumberOfMasterRoom(numberOfMasterRooms
                ).and(whichNumberOfSingleRooms(numberOfSingleRooms).and(whichAmount(amount).and(whichCreatedDate(createdDate)))))));

        return housing.stream().map(h -> toDto(h)).collect(Collectors.toList());
    }


    public Specification<Housing> whichHousingName(Optional<String> housingName){
        return housingName.isEmpty() ? Specification.where(null) :
                (root, query, cb) -> cb.like(cb.lower(root.get("housingName")), housingName.get().toLowerCase().concat("%"));
    }
    public Specification<Housing> whichNumberOfFloors(Optional<Integer> numberOfFloors){
        return !numberOfFloors.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("numberOfFloors"), numberOfFloors.get());
    }

    public Specification<Housing> whichNumberOfMasterRoom(Optional<Integer> numberOfMasterRooms){
        return !numberOfMasterRooms.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("numberOfMasterRoom"), numberOfMasterRooms.get());
    }

    public Specification<Housing> whichNumberOfSingleRooms(Optional<Integer> numberOfSingleRooms){
        return !numberOfSingleRooms.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("numberOfSingleRoom"), numberOfSingleRooms.get());
    }

    public Specification<Housing> whichAmount(Optional<Double> amount){
        return !amount.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("amount"), amount.get());
    }

    public Specification<Housing> whichCreatedDate(Optional<LocalDate> createdDate) {
        return !createdDate.isPresent() ? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("createdDate"), createdDate.get());
    }

    public Page<HousingDto> paginationHousing(Long ownerId, int pageNumber, int pageSize) {

        var pageable = PageRequest.of(pageNumber,pageSize);
        var list = housingRepo.findHousingsByOwnerId(ownerId, pageable).stream().map(h -> toDto(h)).collect(Collectors.toList());
        Page<HousingDto> page= new PageImpl<>(list);
        return page ;
    }

    public HousingDto update(HousingDto dto, Long id) {
        var housing = housingRepo.findById(id).orElseThrow(() ->new DataNotFoundException("There is no data."));
        housing.setHousingName(dto.getHousingName());
        housing.setAddress(dto.getAddress());
        housing.setNumberOfFloors(dto.getNumberOfFloors());
        housing.setNumberOfMasterRoom(dto.getNumberOfMasterRoom());
        housing.setNumberOfSingleRoom(dto.getNumberOfSingleRoom());
        housing.setAmount(dto.getAmount());
        housingRepo.save(housing);
        return dto;
    }

   
}
