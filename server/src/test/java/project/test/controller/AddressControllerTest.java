package project.test.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static project.test.compose.Compose.address;
import static project.test.compose.Compose.city;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import project.test.ApplicationTest;
import project.test.compose.Compose;
import project.test.domain.Address;
import project.test.domain.City;
import project.test.domain.State;
import project.test.domain.Zip;
import project.test.dto.AddressDTO;
import project.test.repository.AddressRepository;
import project.test.utils.MapperUtils;

public class AddressControllerTest extends ApplicationTest {

	private MapperUtils<Address, AddressDTO> convert = new MapperUtils<Address, AddressDTO>(Address.class,
			AddressDTO.class);

	@Autowired
	private AddressRepository repository;

	@Test
	public void testCreate() throws JsonProcessingException, IOException {
		Zip z = Compose.zip("04429060").build();
		State s = Compose.state("SP").build();
		City city = city("SãoPaulo", s).build();
		saveall(z, s, city);

		Address address = address(z, city).number(1).street("Rua x").complement("complement").district("district")
				.build();

		ResponseEntity<AddressDTO> response = post("/address").json(new AddressDTO(address))
				.expectedStatus(HttpStatus.CREATED).getResponse(AddressDTO.class);

		assertThat(response.getBody().getId(), notNullValue());
		assertThat(response.getBody().getStreet(), equalTo(address.getStreet()));
		assertThat(response.getBody().getNumber(), equalTo(address.getNumber()));
		assertThat(repository.findAll(), hasSize(1));
	}

	@Test
	public void testUpdate() {
		Zip z = Compose.zip("04429060").build();
		State s = Compose.state("SP").build();
		City c1 = city("A-city", s).build();
		Address address = address(z, c1).number(1).street("Rua x").complement("complement").district("district")
				.build();
		saveall(s, c1, z, address);

		String newStreet = "Rua A";
		Integer newNumber = 2;
		address.setStreet(newStreet);
		address.setNumber(newNumber);

		ResponseEntity<AddressDTO> response = put("/address/%s", address.getId()).json(new AddressDTO(address))
				.expectedStatus(HttpStatus.OK).getResponse(AddressDTO.class);

		assertThat(response.getBody().getStreet(), equalTo(address.getStreet()));
		assertThat(response.getBody().getNumber(), equalTo(address.getNumber()));

		Address a2 = repository.findOne(address.getId());

		assertThat(a2.getStreet(), equalTo(newStreet));
		assertThat(a2.getNumber(), equalTo(newNumber));
	}

	@Test
	public void testRead() {
		Zip z = Compose.zip("04429060").build();
		State s = Compose.state("SP").build();
		City c1 = city("A-city", s).build();
		Address address = address(z, c1).number(1).street("Rua x").complement("complement").district("district")
				.build();
		saveall(s, c1, z, address);

		ResponseEntity<AddressDTO> response = get("/address/%s", address.getId()).expectedStatus(HttpStatus.OK)
				.getResponse(AddressDTO.class);

		assertThat(response.getBody().getComplement(), equalTo(address.getComplement()));
		assertThat(response.getBody().getDistrict(), equalTo(address.getDistrict()));
		assertThat(response.getBody().getStreet(), equalTo(address.getStreet()));
		assertThat(response.getBody().getNumber(), equalTo(address.getNumber()));
	}

	@Test
	public void testDelete() {
		Zip z = Compose.zip("04429060").build();
		State s = Compose.state("SP").build();
		City c1 = city("A-city", s).build();
		Address address = address(z, c1).number(1).street("Rua x").complement("complement").district("district")
				.build();
		saveall(s, c1, z, address);

		assertThat(repository.findAll(), hasSize(1));

		ResponseEntity<AddressDTO> response = delete("/address/%s", address.getId()).expectedStatus(HttpStatus.OK)
				.getResponse(AddressDTO.class);

		assertThat(response.getBody().getId(), equalTo(address.getId()));
		assertThat(repository.findAll(), hasSize(0));
	}

	@Test
	public void testReadNotFound() {
		Zip z = Compose.zip("04429060").build();
		State s = Compose.state("SP").build();
		City c1 = city("A-city", s).build();
		Address address = address(z, c1).number(1).street("Rua x").complement("complement").district("district")
				.build();
		saveall(s, c1, z, address);
		get("/address/1").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
	}

	@Test
	public void testDeleteNotFound() {
		Zip z = Compose.zip("04429060").build();
		State s = Compose.state("SP").build();
		City c1 = city("A-city", s).build();
		Address address = address(z, c1).number(1).street("Rua x").complement("complement").district("district")
				.build();
		saveall(s);
		saveall(c1);
		saveall(z);
		saveall(address);
		delete("/address/2").expectedStatus(HttpStatus.NOT_FOUND).getResponse();
	}

	@Test
	public void testUpdateNotFound() {
		Zip z = Compose.zip("04429060").build();
		State s = Compose.state("SP").build();
		City c1 = city("A-city", s).build();
		Address address = address(z, c1).number(1).street("Rua x").complement("complement").district("district")
				.build();
		saveall(s, c1, z, address);
		put("/address/%s", address.getId() + 1).json(convert.toDTO(address)).expectedStatus(HttpStatus.NOT_FOUND)
				.getResponse();
	}
}
