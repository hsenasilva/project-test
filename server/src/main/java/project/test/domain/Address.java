package project.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode
@ToString
@Table(name = "address")
public class Address {

	@GeneratedValue
	@Id
	@Column(name = "address_id")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "zip_id", foreignKey = @ForeignKey(name = "FK_ZIP_ADDRESS"))
	private Zip zipcode;

	@Column(name = "address_street", nullable = false, length = 100)
	private String street;
	
	@Column(name = "address_district", nullable = false, length = 100)
	private String district;
	
	@Column(name = "address_complement", nullable = false, length = 100)
	private String complement;

	@Column(name = "address_number", nullable = false, length = 10)
	private Integer number;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_CITY_ADDRESS"))
	private City city;
}
