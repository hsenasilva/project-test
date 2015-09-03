package project.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import project.test.domain.Address;
import project.test.domain.City;
import project.test.domain.Zip;
import project.test.dto.AddressDTO;
import project.test.dto.ZipDTO;
import project.test.exception.NotFoundException;
import project.test.repository.AddressRepository;
import project.test.repository.CityRepository;
import project.test.service.ZipService;
import project.test.utils.MapperUtils;

@RestController
@RequestMapping("/address")
public class AddressController {

	private MapperUtils<Address, AddressDTO> convert = new MapperUtils<Address, AddressDTO>(Address.class,
			AddressDTO.class);
	private MapperUtils<Zip, ZipDTO> convertZip = new MapperUtils<Zip, ZipDTO>(Zip.class, ZipDTO.class);

	@Autowired
	private AddressRepository repository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ZipService zipService;

	// @Transactional(readOnly = true)
	// @RequestMapping(method = RequestMethod.GET)
	// public Page<AddressDTO> list(@PageableDefault(page = 0, size = 50, sort =
	// "name") Pageable page) {
	//
	// Page<Address> result = repository.findAll(page);
	//
	// return new PageImpl<AddressDTO>(result.getContent().stream().map(c -> new
	// AddressDTO(c)).collect(Collectors.toList()),
	// page, result.getTotalElements());
	// }

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public List<Address> listAll() {
		List<Address> result = repository.findAll();
		return result;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public AddressDTO create(@Valid @RequestBody AddressDTO dto) {
		ZipDTO zipDto = zipService.findZip(dto.getZipcodeNumber());
		Zip zip = convertZip.toEntity(zipDto);
		City city = cityRepository.findByName(dto.getCity());
		if (city == null) {
			throw new NotFoundException(City.class);
		}

		Address entity = new AddressDTO().dtoToAddress(dto, zip, city);
		entity = repository.save(entity);
		return new AddressDTO(entity);
	}

	@Transactional
	@RequestMapping(value = "/{ref}", method = RequestMethod.PUT)
	@ResponseBody
	public AddressDTO update(@PathVariable("ref") Long ref, @Valid @RequestBody AddressDTO dto) {
		Address entity = repository.findOne(ref);
		if (entity == null) {
			throw new NotFoundException(Address.class);
		}
		convert.updateEntity(entity, dto, "id", "natures");
		entity = repository.save(entity);
		return convert.toDTO(entity);
	}

	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public AddressDTO delete(@PathVariable("id") Long id) {
		Address address = repository.findOne(id);

		if (address == null) {
			throw new NotFoundException(Address.class);
		}

		this.repository.delete(address);
		return new AddressDTO(address);
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public AddressDTO read(@PathVariable("id") Long id) {

		Address address = repository.findOne(id);
		if (address == null) {
			throw new NotFoundException(Address.class);
		}
		return new AddressDTO(address);
	}

//	@Transactional(readOnly = true)
//	@RequestMapping(method = RequestMethod.GET)
//	public Stream testStream() {
//		
//		Stream.iterate("a", "b", "A", "B", "A", "B", "a", "c")
//		Stream.of("a1", "a2", "a3").forEach(action);
//			.findFirst()
//			.ifPresent(System.out::println); // a1
//		
//		return null;
//	}

}
