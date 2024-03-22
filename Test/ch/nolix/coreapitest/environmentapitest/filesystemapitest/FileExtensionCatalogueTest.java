//package declaration
package ch.nolix.coreapitest.environmentapitest.filesystemapitest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.environmentapi.filesystemapi.FileExtensionCatalogue;

//class
public final class FileExtensionCatalogueTest extends StandardTest {

  //method
  @Test
  public void testCase_constants() {

    //verification
    for (final var c : GlobalClassTool.getPublicStaticFieldValuesOfClass(FileExtensionCatalogue.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
