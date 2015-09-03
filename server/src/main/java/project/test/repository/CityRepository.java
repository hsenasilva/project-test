package project.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.test.domain.City;

public interface CityRepository extends JpaRepository<City, Long> {

//	@Query("select c from City c where UPPER(c.name) like UPPER(?1) or UPPER(c.state) like UPPER(?1) or UPPER(c.country) like UPPER(?1)")
//	Page<City> search(String value, Pageable page);
	@Query("select c from City c where c.name = ?1")
	City findByName(String name);
	
}
