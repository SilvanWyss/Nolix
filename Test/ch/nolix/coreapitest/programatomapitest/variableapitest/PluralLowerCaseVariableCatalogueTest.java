//package declaration
package ch.nolix.coreapitest.programatomapitest.variableapitest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

//class
public final class PluralLowerCaseVariableCatalogueTest extends StandardTest {

  //method
  @Test
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
