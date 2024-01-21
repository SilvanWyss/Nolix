//package declaration
package ch.nolix.coretest.programatomtest.nametest;

import ch.nolix.core.commontypetool.GlobalStringHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class LowerCaseCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //setup
    final var exceptions = LinkedList.withElement(LowerCaseVariableCatalogue.GUI, LowerCaseVariableCatalogue.URL);

    //verification
    for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(LowerCaseVariableCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringHelper::isLowerCase);
      }
    }
  }
}
