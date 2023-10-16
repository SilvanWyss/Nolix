//package declaration
package ch.nolix.coretest.environmenttest.filesystemtest;

//own imports
import ch.nolix.core.environment.filesystem.FileExtensionCatalogue;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class FileExtensionCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //verification
    for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(FileExtensionCatalogue.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
