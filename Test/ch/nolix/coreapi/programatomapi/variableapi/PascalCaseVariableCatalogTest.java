package ch.nolix.coreapi.programatomapi.variableapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.stringtool.StringExaminer;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.structurecontrol.reflectiontool.ReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringExaminer;

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
