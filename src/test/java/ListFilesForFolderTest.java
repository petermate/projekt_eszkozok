package test.java;

import static org.junit.Assert.*;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import file_reader.file_reader.EU4FileReader;

public class ListFilesForFolderTest {

	@Test
	public void test() {
		EU4FileReader fr = new EU4FileReader();
		File file = new File("./assets/common/technologies");
		List list = new ArrayList<>();
		list.add("mil.txt");
		List<String> exp = fr.listFilesForFolder(file);
		assertEquals(list, exp);
	}

}
