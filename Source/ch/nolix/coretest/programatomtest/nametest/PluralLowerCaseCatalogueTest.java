//package declaration
package ch.nolix.coretest.programatomtest.nametest;

import ch.nolix.core.commontypetool.GlobalStringHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.programatomapi.variablenameapi.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class PluralLowerCaseCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //setup
    final var exceptions = LinkedList.withElement(PluralLowerCaseCatalogue.GUIS, PluralLowerCaseCatalogue.URLS);

    //verification
    for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(PluralLowerCaseCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringHelper::isLowerCase);
      }
    }
  }
}
