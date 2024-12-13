package ch.nolix.coreapitest.programatomapitest.stringcatalogueapitest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

final class StringCatalogueTest extends StandardTest {

  @Test
  void testCase_constants() {

    //verification
    for (final var f : StringCatalogue.class.getFields()) {
      expect(GlobalReflectionTool.isPublic(f)).isTrue();
      expect(GlobalReflectionTool.isStatic(f)).isTrue();
      expect(GlobalReflectionTool.getValueFromStaticField(f).getClass()).is(String.class);
    }
  }
}
