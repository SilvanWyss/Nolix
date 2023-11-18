//package declaration
package ch.nolix.coreapitest.commontypeapitest.stringutilapitest;

//own imports
import ch.nolix.core.reflection.GlobalFieldHelper;
import ch.nolix.core.reflection.GlobalMemberHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;

//class
public final class StringCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constants() {

    //verification
    for (final var f : StringCatalogue.class.getFields()) {
      expect(GlobalMemberHelper.isPublic(f));
      expect(GlobalFieldHelper.isStatic(f));
      expect(GlobalFieldHelper.getValueFromStaticField(f).getClass()).is(String.class);
    }
  }
}
