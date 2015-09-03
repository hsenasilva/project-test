package project.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.test.domain.Zip;
import project.test.dto.ZipDTO;
import project.test.exception.NotFoundException;
import project.test.repository.ZipRepository;

@Service
public class ZipService {

	@Autowired
	private ZipRepository zipRepository;
	
	public ZipDTO findZip(String code){
		Zip zip = zipRepository.findByZip(code);
		if(zip == null){
			for (int i = code.length(); i > 0; i--) {
				
				Integer lastNumber = i-1;
				String newCode = code.substring(0, lastNumber);
				
				if (newCode.length() < 8) {
					for (int j = newCode.length(); j < 8; j++) {
						newCode += "0";
					}
				}
				
				zip = zipRepository.findByZip(newCode);
				if(zip != null)
					return new ZipDTO(zip);
				
				if (newCode.equals("00000000")) 
					throw new NotFoundException(Zip.class);
			}
		}
		return new ZipDTO(zip);
	}
	
}
