//package declaration
package ch.nolix.coreapitest.programatomapitest.variableapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;

//class
final class PascalCaseVariableCatalogueTest extends StandardTest {

  //constant
  private static final IStringTool STRING_TOOL = new StringTool();

  //method
  @Test
  void testCase_constants() {

    //setup
    final var exceptions = LinkedList.withElement(PascalCaseVariableCatalogue.GUI, PascalCaseVariableCatalogue.URL);

    //verification
    for (final var c : GlobalClassTool.getPublicStaticFieldValuesOfClass(PascalCaseVariableCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(STRING_TOOL::isPascalCase);
      }
    }
  }
}
