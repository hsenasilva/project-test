package project.test.service;

public class StreamServiceImpl implements StreamService {

	private String input;
	private int counter = 0;

	@Override
	public char getNext() {
		if (hasNext()) {
			return input.charAt(counter++);
		}
		return input.charAt(input.length() - 1);
	}

	@Override
	public boolean hasNext() {
		return (counter < input.length());
	}

	public StreamServiceImpl(String input) {
		this.input = input;
	}

}
