package ch.nolix.coreapi.programatomapi.stringcatalogapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.structurecontrol.reflectiontool.ReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;

final class StringCatalogTest extends StandardTest {

  @Test
  void testCase_constants() {

    //verification
    for (final var f : StringCatalog.class.getFields()) {
      expect(ReflectionTool.isPublic(f)).isTrue();
      expect(ReflectionTool.isStatic(f)).isTrue();
      expect(ReflectionTool.getValueFromStaticField(f).getClass()).is(String.class);
    }
  }
}
