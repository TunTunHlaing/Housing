package com.example.housing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import com.example.housing.entity.Housing;
import com.example.housing.repo.HousingRepo;
import com.example.housing.repo.OwnerRepo;

@SpringBootApplication
public class HousingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousingApplication.class, args);
	}

	/*
	 * @Autowired private PasswordEncoder encoder;
	 * 
	 * @Autowired private RoleRepo roleRepo;
	 */
	@Autowired
	private OwnerRepo ownerRepo;
	@Autowired
	private HousingRepo repo;

	@Bean
	@Profile("dev")
	@Transactional
	public ApplicationRunner runner() {
		return args -> {

			// For Creating First User with Admin Role. IF you finished, please create owner
			// By user with Admin role

			/*
			 * Role ownerRole = new Role(); ownerRole.setRoleName("OWNER"); Role adminRole =
			 * new Role(); adminRole.setRoleName("ADMIN"); Owner owner = new Owner(
			 * "kyaw kyaw", "kyaw@gmail.com", encoder.encode("22222"), LocalDate.now(),
			 * LocalDate.now()); owner.addRoles(ownerRole); roleRepo.save(ownerRole);
			 * ownerRepo.save(owner);
			 */

			// For save After Run sql file/ owner_id can change with the user_id that
			// created by owner
			var owner = ownerRepo.findById(11L).get();

			List<Housing> h = repo.findHousingsByOwnerId(11L);
			h.forEach(a -> owner.addHousing(a));
			h.forEach(a -> repo.save(a));
			ownerRepo.save(owner);

			owner.getHousingList().forEach(System.out::println);

		};
	}
}
