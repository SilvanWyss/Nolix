/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalcatalog.variablenamecatalog;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.stringexaminer.StringExaminer;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
final class PluralLowerCaseVariableCatalogTest extends StandardTest {
  private static final StringExaminer STRING_EXAMINER = new StringExaminer();

  @Test
  void testCase_constants() {
    // setup
    final var exceptions = //
    LinkedList.withElement(PluralLowerCaseVariableNameCatalog.GUIS, PluralLowerCaseVariableNameCatalog.URLS);

    // verify
    for (final var c : ReflectionTool
      .getStoredPublicStaticFieldValuesOfClass(PluralLowerCaseVariableNameCatalog.class)) {
      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqual(stringValue)) {
        expect(stringValue).fulfills(STRING_EXAMINER::isLowerCase);
      }
    }
  }
}
