package project.test.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class CityDTO {

	private Long id;

//	@NotNull(message = "property name can't be null")
	private String name;

//	@NotNull(message = "property state can't be null")
	private String state;

}
