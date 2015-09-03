package project.test.compose;

import project.test.domain.Address;
import project.test.domain.City;
import project.test.domain.City.CityBuilder;
import project.test.domain.State;
import project.test.domain.Zip;

public class Compose {

	public static State.StateBuilder state(String name) {
		return State.builder().name(name).uf("uf");
	}
	
	public static CityBuilder city(String name, State state) {
		return City.builder().name(name).state(state);
	}

	public static Zip.ZipBuilder zip(String zip) {
		return Zip.builder().district("bairro").zip(zip).complement("complemento").locality("São Paulo").address("Praça da Sé").name("nome").uf("SP");
	}
	
	public static Address.AddressBuilder address(Zip zip, City city) {
		return Address.builder().zipcode(zip).street("rua").number(1).city(city);
	}
}
