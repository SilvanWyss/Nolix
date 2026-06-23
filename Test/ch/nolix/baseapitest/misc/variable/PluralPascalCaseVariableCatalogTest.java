/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.misc.variable;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.misc.variablenamecatalog.PluralPascalCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
final class PluralPascalCaseVariableCatalogTest extends StandardTest {
  @Test
  void testCase_constants() {
    //setup
    final var exceptions = //
    LinkedList.withElement(PluralPascalCaseVariableNameCatalog.GUIS, PluralPascalCaseVariableNameCatalog.URLS);

    //verification
    for (final var c : ReflectionTool
      .getStoredPublicStaticFieldValuesOfClass(PluralPascalCaseVariableNameCatalog.class)) {
      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(StringTool::isPascalCase);
      }
    }
  }
}
