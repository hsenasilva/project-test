package project.test.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;

import project.test.service.StreamService;

@Controller
public class StreamController {

	
	public Character firstChar(StreamService input) {
		Set<Character> chars = new LinkedHashSet<Character>();
		Character c = null;

		if (input == null)
			return null;

		while (input.hasNext()) {
			c = input.getNext();

			if (chars.contains(c)) {
				chars.remove(c);
			} else {
				chars.add(c);
			}
		}
		return (Character) chars.toArray()[0];
	}


//
//	public StreamController(String input, int counter) {
//		super();
//		this.input = input;
//		this.counter = counter;
//	}

	// public char firstChar(Stream input){
	// String x = "teste";
	// List<String> palavras = Arrays.asList("aAbBABac");
	// palavras.stream()
	// .forEach(p -> System.out.println(p));
	// return 0;
	//
	// }

	// Comparator<String> comparador = (s1, s2) -> {
	// s1.rep
	//

	// return Integer.compare(s1.length(), s2.length());
	// };

}
