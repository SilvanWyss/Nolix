package ch.nolix.coreapi.programatomapi.stringcatalogapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.structurecontrol.reflectiontool.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;

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
