//package declaration
package ch.nolix.coreapitest.programatomapitest.variableapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.stringtool.GlobalStringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalogue;

//class
final class PluralPascalCaseVariableCatalogueTest extends StandardTest {

  //method
  @Test
  void testCase_constants() {

    //setup
    final var exceptions = //
    LinkedList.withElement(PluralPascalCaseVariableCatalogue.GUIS, PluralPascalCaseVariableCatalogue.URLS);

    //verification
    for (final var c : GlobalClassTool.getPublicStaticFieldValuesOfClass(PluralPascalCaseVariableCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringTool::isPascalCase);
      }
    }
  }
}
