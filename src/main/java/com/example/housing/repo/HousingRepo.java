package com.example.housing.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.housing.entity.Housing;

public interface HousingRepo extends JpaRepositoryImplementation<Housing, Long>,
        PagingAndSortingRepository<Housing,Long> {


    List<Housing> findHousingsByOwnerId(Long id, Pageable pageable);
    
    List<Housing> findHousingsByOwnerId(Long id);
    
    List<Housing>  findByOwnerId(Long ownerId);
}
