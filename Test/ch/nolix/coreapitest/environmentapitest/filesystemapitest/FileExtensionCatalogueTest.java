package ch.nolix.coreapitest.environmentapitest.filesystemapitest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.environmentapi.filesystemapi.FileExtensionCatalogue;

final class FileExtensionCatalogueTest extends StandardTest {

  @Test
  void testCase_constants() {

    //verification
    for (final var c : GlobalReflectionTool.getPublicStaticFieldValuesOfClass(FileExtensionCatalogue.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
