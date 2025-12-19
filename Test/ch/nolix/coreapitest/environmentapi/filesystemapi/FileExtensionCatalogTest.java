package ch.nolix.coreapitest.environmentapi.filesystemapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.environment.filesystem.FileExtensionCatalog;

/**
 * @author Silvan Wyss
 */
final class FileExtensionCatalogTest extends StandardTest {
  @Test
  void testCase_constants() {
    //verification
    for (final var c : ReflectionTool.getStoredPublicStaticFieldValuesOfClass(FileExtensionCatalog.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
