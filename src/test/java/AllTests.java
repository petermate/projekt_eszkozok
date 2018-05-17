package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.java.GetFileContentTest;
import test.java.GetUnitWithAttributesTest;
import test.java.ListFilesForFolderTest;
import test.java.GetUnlockedUnitsTest;

@RunWith(Suite.class)
@SuiteClasses({GetUnitWithAttributesTest.class, GetFileContentTest.class, ListFilesForFolderTest.class, GetUnlockedUnitsTest.class,
                CasualtyTest.class, CombatUnitTest.class})
public class AllTests {

}
