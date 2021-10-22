package client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {
		
	@Test
	void testFileReader() {
		System.out.println(Main.readFile("test.txt"));
		assert(Main.readFile("test.txt").equals("133 4"));
	}

}
