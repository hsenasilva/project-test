package project.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "zipcode")
@EqualsAndHashCode
@ToString
public class Zip {

	@Id
	private String zip;
	
	@Column(name="ufe_sg")
	private String uf;

	@Column(name="loc_no")
	private String locality;

	@Column(name="dis_no")
	private String district;
	
	@Column(name="addr_no")
	private String address;
	
	@Column(name="addr_complement")
	private String complement;
	
	private String name;

}
