//package declaration
package ch.nolix.coreapitest.programatomapitest.variableapitest;

//own imports
import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class PluralLowerCaseVariableCatalogueTest extends StandardTest {

  //method
  @TestCase
  public void testCase_constants() {

    //setup
    final var exceptions = //
    LinkedList.withElement(PluralLowerCaseVariableCatalogue.GUIS, PluralLowerCaseVariableCatalogue.URLS);

    //verification
    for (final var c : GlobalClassTool.getPublicStaticFieldValuesOfClass(PluralLowerCaseVariableCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringTool::isLowerCase);
      }
    }
  }
}
