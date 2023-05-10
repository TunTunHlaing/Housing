package com.example.housing.repo;

import com.example.housing.entity.Owner;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface OwnerRepo extends JpaRepositoryImplementation<Owner, Long> {


    Optional<Owner> findOwnerByEmail(String username);
}
