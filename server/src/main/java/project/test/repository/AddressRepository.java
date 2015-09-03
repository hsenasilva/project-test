package project.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.test.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
