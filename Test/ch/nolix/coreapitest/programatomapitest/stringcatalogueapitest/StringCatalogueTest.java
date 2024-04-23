//package declaration
package ch.nolix.coreapitest.programatomapitest.stringcatalogueapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.reflection.GlobalFieldTool;
import ch.nolix.core.reflection.GlobalMemberTool;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

//class
final class StringCatalogueTest extends StandardTest {

  //method
  @Test
  void testCase_constants() {

    //verification
    for (final var f : StringCatalogue.class.getFields()) {
      expect(GlobalMemberTool.isPublic(f));
      expect(GlobalFieldTool.isStatic(f));
      expect(GlobalFieldTool.getValueFromStaticField(f).getClass()).is(String.class);
    }
  }
}