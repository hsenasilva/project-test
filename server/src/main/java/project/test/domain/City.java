package project.test.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

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
@EnableAutoConfiguration
@EqualsAndHashCode(exclude = "state")
@ToString(exclude = "state")
@Table(name = "city")
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5466851602929072707L;

	@GeneratedValue
	@Id
	@Column(name = "city_id")
	private Long id;

	@Column(name = "city_name", nullable = false, length = 200)
	private String name;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "FK_CITY_STATE"))
	private State state;

}
