/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.programatomapi.variableapi;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontypetool.stringtool.StringExaminer;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.commontypetool.stringtool.IStringExaminer;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
final class PascalCaseVariableCatalogTest extends StandardTest {
  private static final IStringExaminer STRING_EXAMINER = new StringExaminer();

  @Test
  void testCase_constants() {
    //setup
    final var exceptions = LinkedList.withElement(PascalCaseVariableCatalog.GUI, PascalCaseVariableCatalog.URL);

    //verification
    for (final var c : ReflectionTool.getStoredPublicStaticFieldValuesOfClass(PascalCaseVariableCatalog.class)) {
      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(STRING_EXAMINER::isPascalCase);
      }
    }
  }
}
