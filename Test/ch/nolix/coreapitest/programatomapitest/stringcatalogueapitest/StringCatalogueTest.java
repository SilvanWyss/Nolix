//package declaration
package ch.nolix.coreapitest.programatomapitest.stringcatalogueapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

//class
final class StringCatalogueTest extends StandardTest {

  //method
  @Test
  void testCase_constants() {

    //verification
    for (final var f : StringCatalogue.class.getFields()) {
      expect(GlobalReflectionTool.isPublic(f));
      expect(GlobalReflectionTool.isStatic(f));
      expect(GlobalReflectionTool.getValueFromStaticField(f).getClass()).is(String.class);
    }
  }
}
