package ch.nolix.coreapitest.environmentapitest.filesystemapitest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.environmentapi.filesystemapi.FileExtensionCatalog;

final class FileExtensionCatalogTest extends StandardTest {

  @Test
  void testCase_constants() {

    //verification
    for (final var c : GlobalReflectionTool.getPublicStaticFieldValuesOfClass(FileExtensionCatalog.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
