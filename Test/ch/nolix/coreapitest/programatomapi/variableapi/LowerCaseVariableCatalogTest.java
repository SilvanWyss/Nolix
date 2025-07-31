package ch.nolix.coreapitest.programatomapi.variableapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.stringtool.StringExaminer;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetool.stringtool.IStringExaminer;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;

final class LowerCaseVariableCatalogTest extends StandardTest {

  private static final IStringExaminer STRING_EXAMINER = new StringExaminer();

  @Test
  void testCase_constants() {

    //setup
    final var exceptions = LinkedList.withElement(LowerCaseVariableCatalog.GUI, LowerCaseVariableCatalog.URL);

    //verification
    for (final var c : ReflectionTool.getStoredPublicStaticFieldValuesOfClass(LowerCaseVariableCatalog.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(STRING_EXAMINER::isLowerCase);
      }
    }
  }
}
