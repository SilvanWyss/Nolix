package ch.nolix.coreapi.environmentapi.filesystemapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.structurecontrol.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;

final class FileExtensionCatalogTest extends StandardTest {

  @Test
  void testCase_constants() {

    //verification
    for (final var c : GlobalReflectionTool.getStoredPublicStaticFieldValuesOfClass(FileExtensionCatalog.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
