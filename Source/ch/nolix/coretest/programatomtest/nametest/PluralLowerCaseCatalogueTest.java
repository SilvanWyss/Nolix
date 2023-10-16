//package declaration
package ch.nolix.coretest.programatomtest.nametest;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

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
