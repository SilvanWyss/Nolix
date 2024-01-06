//package declaration
package ch.nolix.coretest.programatomtest.nametest;

import ch.nolix.core.commontypetool.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;

//class
public final class PascalCaseCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //setup
    final var exceptions = LinkedList.withElement(PascalCaseCatalogue.GUI, PascalCaseCatalogue.URL);

    //verification
    for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(PascalCaseCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringHelper::isPascalCase);
      }
    }
  }
}
