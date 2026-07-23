/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalcatalog.textcatalog;

import org.junit.jupiter.api.Test;

import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
final class StringCatalogTest extends StandardTest {
  @Test
  void testCase_constants() {
   // verify
    for (final var f : StringCatalog.class.getFields()) {
      expect(ReflectionTool.isPublic(f)).isTrue();
      expect(ReflectionTool.isStatic(f)).isTrue();
      expect(ReflectionTool.getValueOfStaticField(f).getClass()).is(String.class);
    }
  }
}
