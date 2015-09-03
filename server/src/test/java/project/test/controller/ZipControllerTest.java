package project.test.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import project.test.ApplicationTest;
import project.test.compose.Compose;
import project.test.domain.State;
import project.test.domain.Zip;
import project.test.dto.ZipDTO;
import project.test.repository.ZipRepository;

public class ZipControllerTest extends ApplicationTest{

	@Autowired
	private ZipRepository repository;
	
	
	@Test
	public void testSearchByZip() throws Exception{
		State s = Compose.state("SP").build();
		Compose.city("São Paulo", s).build();
		Zip c = Compose.zip("01001000").build();
		saveall(c);
		
		ResponseEntity<ZipDTO> response = get("/zip/find").queryParam("code", c.getZip()).getResponse(ZipDTO.class);
		
		assertThat(response.getBody().getZip(), equalTo(c.getZip().toString()));
		assertThat(response.getBody().getDistrict(), equalTo(c.getDistrict().toString()));
		assertThat(response.getBody().getLocality(), equalTo(c.getLocality().toString()));
		assertThat(response.getBody().getAddress(), equalTo(c.getAddress().toString()));
		assertThat(response.getBody().getUf(), equalTo(c.getUf().toString()));
		assertThat(repository.findAll(),  hasSize(1));
	}
	
	@Test
	public void testSearchByInvalidZip() throws Exception{
		
		
		Zip c = Compose.zip("010010000").build();
		saveall(c);
		get("/zip/find").queryParam("code", c.getZip()).expectedStatus(HttpStatus.BAD_REQUEST).getResponse(ZipDTO.class);
	}
	
	@Test
	public void testSearchRegressive() throws Exception{
		Zip c = Compose.zip("01001000").build();
		saveall(c);
		
		ResponseEntity<ZipDTO> response = get("/zip/find").queryParam("code", "01001444").getResponse(ZipDTO.class);
		
		assertThat(response.getBody().getZip(), equalTo(c.getZip().toString()));
		assertThat(response.getBody().getDistrict(), equalTo(c.getDistrict().toString()));
		assertThat(response.getBody().getLocality(), equalTo(c.getLocality().toString()));
		assertThat(response.getBody().getAddress(), equalTo(c.getAddress().toString()));
		assertThat(response.getBody().getUf(), equalTo(c.getUf().toString()));
		assertThat(repository.findAll(),  hasSize(1));
	}
	
	@Test
	public void testSearchByZipNotFound() throws Exception{
		Zip c = Compose.zip("01001000").build();
		saveall(c);
		
		get("/zip/find").queryParam("code", "12345678").expectedStatus(HttpStatus.NOT_FOUND).getResponse(ZipDTO.class);
	}
}
