//package declaration
package ch.nolix.coretest.programatomtest.nametest;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class LowerCaseCatalogueTest extends Test {

  // method
  @TestCase
  public void testCase_constants() {

    // setup
    final var exceptions = LinkedList.withElement(LowerCaseCatalogue.GUI, LowerCaseCatalogue.URL);

    // verification
    for (final var c : GlobalClassHelper.getPublicStaticFieldValuesOfClass(LowerCaseCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(GlobalStringHelper::isLowerCase);
      }
    }
  }
}
