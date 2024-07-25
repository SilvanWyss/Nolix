//package declaration
package ch.nolix.coreapitest.environmentapitest.filesystemapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.environmentapi.filesystemapi.FileExtensionCatalogue;

//class
final class FileExtensionCatalogueTest extends StandardTest {

  //method
  @Test
  void testCase_constants() {

    //verification
    for (final var c : GlobalReflectionTool.getPublicStaticFieldValuesOfClass(FileExtensionCatalogue.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
