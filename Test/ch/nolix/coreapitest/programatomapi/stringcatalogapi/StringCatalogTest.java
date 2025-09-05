package ch.nolix.coreapitest.programatomapi.stringcatalogapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;

final class StringCatalogTest extends StandardTest {
  @Test
  void testCase_constants() {
    //verification
    for (final var f : StringCatalog.class.getFields()) {
      expect(ReflectionTool.isPublic(f)).isTrue();
      expect(ReflectionTool.isStatic(f)).isTrue();
      expect(ReflectionTool.getValueOfStaticField(f).getClass()).is(String.class);
    }
  }
}
