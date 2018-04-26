package file_reader.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.java.GetFileContentTest;
import test.java.GetUnitWithAttributesTest;
import test.java.ListFilesForFolderTest;
import test.java.getUnlockedUnitsTest;

@RunWith(Suite.class)
@SuiteClasses({GetUnitWithAttributesTest.class, GetFileContentTest.class, ListFilesForFolderTest.class, getUnlockedUnitsTest.class })
public class AllTests {

}
