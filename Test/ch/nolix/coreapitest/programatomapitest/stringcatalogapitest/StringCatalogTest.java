package ch.nolix.coreapitest.programatomapitest.stringcatalogapitest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;

final class StringCatalogTest extends StandardTest {

  @Test
  void testCase_constants() {

    //verification
    for (final var f : StringCatalog.class.getFields()) {
      expect(GlobalReflectionTool.isPublic(f)).isTrue();
      expect(GlobalReflectionTool.isStatic(f)).isTrue();
      expect(GlobalReflectionTool.getValueFromStaticField(f).getClass()).is(String.class);
    }
  }
}
