package project.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.test.dto.ZipDTO;
import project.test.exception.WebException;
import project.test.service.ZipService;

@RestController
@RequestMapping("/zip")
public class ZipController {
	
	@Autowired
	private ZipService service;

	@Transactional(readOnly = true)
	@RequestMapping(value="/find", method = RequestMethod.GET)
	@ResponseBody
	public ZipDTO findZip(@RequestParam("code") String code) throws Exception{
		
		if (code.length() < 8 || code.length() > 8) 
			throw new WebException(HttpStatus.BAD_REQUEST, "CEP.INVALIDO");
		
		return service.findZip(code);
	}
}