/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.misc.variable;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.misc.variable.PluralPascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
final class PluralPascalCaseVariableCatalogTest extends StandardTest {
  @Test
  void testCase_constants() {
    //setup
    final var exceptions = //
    LinkedList.withElement(PluralPascalCaseVariableCatalog.GUIS, PluralPascalCaseVariableCatalog.URLS);

    //verification
    for (final var c : ReflectionTool
      .getStoredPublicStaticFieldValuesOfClass(PluralPascalCaseVariableCatalog.class)) {
      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(StringTool::isPascalCase);
      }
    }
  }
}
