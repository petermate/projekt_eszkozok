package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GetFileContentTest.class, ListFilesForFolderTest.class, getUnlockedUnitsTest.class })
public class AllTests {

}
