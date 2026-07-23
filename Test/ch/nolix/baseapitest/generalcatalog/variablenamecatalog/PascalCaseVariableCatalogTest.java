/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalcatalog.variablenamecatalog;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
final class PascalCaseVariableCatalogTest extends StandardTest {
  @Test
  void testCase_constants() {
    // setup
    final var exceptions = LinkedList.withElement(PascalCaseVariableNameCatalog.GUI, PascalCaseVariableNameCatalog.URL);

    // verify
    for (final var c : ReflectionTool.getStoredPublicStaticFieldValuesOfClass(PascalCaseVariableNameCatalog.class)) {
      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqual(stringValue)) {
        expect(stringValue).fulfills(StringTool::isPascalCase);
      }
    }
  }
}
