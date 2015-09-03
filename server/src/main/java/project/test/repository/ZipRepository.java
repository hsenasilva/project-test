package project.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.test.domain.Zip;

public interface ZipRepository extends JpaRepository<Zip, Long>{
	
	Zip findByZip(String zip);
	
}
