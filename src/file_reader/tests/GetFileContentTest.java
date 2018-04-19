package file_reader.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import file_reader.file_reader.EU4FileReader;

public class GetFileContentTest {

	@Test
	public void test() {
		EU4FileReader fr = new EU4FileReader();
		fr.getFileContent("./assets/common/units/adal_guerilla_warfare.txt");
	}


}
