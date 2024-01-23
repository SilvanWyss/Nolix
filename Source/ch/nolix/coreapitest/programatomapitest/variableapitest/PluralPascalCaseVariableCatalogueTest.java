//package declaration
package ch.nolix.coreapitest.programatomapitest.variableapitest;

import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class PluralPascalCaseVariableCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //setup
    final var exceptions = //
    LinkedList.withElement(PluralPascalCaseVariableCatalogue.GUIS, PluralPascalCaseVariableCatalogue.URLS);

    //verification
    for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(PluralPascalCaseVariableCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringTool::isPascalCase);
      }
    }
  }
}
