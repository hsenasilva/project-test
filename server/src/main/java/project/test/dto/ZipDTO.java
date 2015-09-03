package project.test.dto;

import lombok.Data;
import project.test.domain.Zip;

@Data
public class ZipDTO {
	
	private String zip;
	private String uf;
	private String locality;
	private String district;
	private String address;
	private String complement;
	private String name;
	
	
	public ZipDTO() {}
	
	public ZipDTO(Zip zip){
		this.district = zip.getDistrict();
		this.zip = zip.getZip();
		this.address = zip.getAddress();
//		if(cep.getComplemento() != null){
//			this.logradouro += " "+cep.getComplemento();
//		}
//		if(cep.getNome() != null && !cep.getNome().equals("")){
//			this.logradouro += ", "+cep.getNome();
//		}
		this.complement = zip.getComplement();
		this.name = zip.getName();
		this.uf = zip.getUf();
		this.locality = zip.getLocality();
	}
}