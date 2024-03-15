//package declaration
package ch.nolix.coreapitest.environmentapitest.filesystemapitest;

//own imports
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.environmentapi.filesystemapi.FileExtensionCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class FileExtensionCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //verification
    for (final var c : GlobalClassTool.getPublicStaticFieldValuesOfClass(FileExtensionCatalogue.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
