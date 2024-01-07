//package declaration
package ch.nolix.coretest.environmenttest.filesystemtest;

import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.environmentapi.filesystemapi.FileExtensionCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

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
