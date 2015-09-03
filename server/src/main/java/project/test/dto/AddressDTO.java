package project.test.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.test.domain.Address;
import project.test.domain.City;
import project.test.domain.Zip;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AddressDTO {

	private Long id;

	@NotEmpty
	private String zipcodeNumber;

	private String street;

	private String district;
	
	private String complement;
	
	private Integer number;
	
	private String city;
	
	private String state;

	public AddressDTO(Address address) {
		super();
		this.id = address.getId();
		this.zipcodeNumber = address.getZipcode().getZip();
		this.street = address.getStreet();
		this.district = address.getDistrict();
		this.complement = address.getComplement();
		this.number = address.getNumber();
		this.city = address.getCity().getName();
		this.state = address.getCity().getState().getUf();
	}
	
	public Address dtoToAddress(AddressDTO dto, Zip zip, City city) {
		Address address = new Address();
		address.setCity(city);
		address.setZipcode(zip);
		address.setId(dto.getId());
		address.setComplement(dto.getComplement());
		address.setDistrict(dto.getDistrict());
		address.setStreet(dto.getStreet());
		address.setNumber(dto.getNumber());
		return address;
	}
}
