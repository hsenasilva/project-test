package project.test.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import project.test.ApplicationTest;
import project.test.service.StreamServiceImpl;

public class StreamTest extends ApplicationTest{

	@Autowired
	private StreamController streamService;
	
	@Test
    public void testFirstCharUnrepeated(){
		StreamServiceImpl s = new StreamServiceImpl("aAbBABac"); 
        Character c = streamService.firstChar(s);
        assertEquals(Character.valueOf('b'), c);
    }
	
}
