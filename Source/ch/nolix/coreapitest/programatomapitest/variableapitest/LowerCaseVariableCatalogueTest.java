//package declaration
package ch.nolix.coreapitest.programatomapitest.variableapitest;

import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class LowerCaseVariableCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //setup
    final var exceptions = LinkedList.withElement(LowerCaseVariableCatalogue.GUI, LowerCaseVariableCatalogue.URL);

    //verification
    for (final var c : GlobalClassTool.getPublicStaticFieldValuesOfClass(LowerCaseVariableCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringTool::isLowerCase);
      }
    }
  }
}
