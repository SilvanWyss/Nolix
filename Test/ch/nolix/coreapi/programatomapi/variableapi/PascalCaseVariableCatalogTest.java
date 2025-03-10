package ch.nolix.coreapi.programatomapi.variableapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.structurecontrol.reflectiontool.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;

final class PascalCaseVariableCatalogTest extends StandardTest {

  private static final IStringTool STRING_TOOL = new StringTool();

  @Test
  void testCase_constants() {

    //setup
    final var exceptions = LinkedList.withElement(PascalCaseVariableCatalog.GUI, PascalCaseVariableCatalog.URL);

    //verification
    for (final var c : GlobalReflectionTool.getStoredPublicStaticFieldValuesOfClass(PascalCaseVariableCatalog.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(STRING_TOOL::isPascalCase);
      }
    }
  }
}
