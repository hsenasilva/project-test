package project.test.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@EqualsAndHashCode
@ToString
@Table(name = "state")
public class State implements Serializable {
	
	/**
   * 
   */
	private static final long serialVersionUID = 4905510811752293436L;

  	@GeneratedValue
	@Id
	private Long id;
	
	private String name;
	
	private String uf;
}