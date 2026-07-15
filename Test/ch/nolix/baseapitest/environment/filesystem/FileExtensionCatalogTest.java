/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.environment.filesystem;

import org.junit.jupiter.api.Test;

import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.environment.filesystem.FileExtensionCatalog;

/**
 * @author Silvan Wyss
 */
final class FileExtensionCatalogTest extends StandardTest {
  @Test
  void testCase_constants() {
    // verification
    for (final var c : ReflectionTool.getStoredPublicStaticFieldValuesOfClass(FileExtensionCatalog.class)) {
      expect(c).isOfType(String.class);
    }
  }
}
