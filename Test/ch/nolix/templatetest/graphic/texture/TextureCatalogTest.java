package ch.nolix.templatetest.graphic.texture;

import org.junit.jupiter.api.Test;

import ch.nolix.core.structurecontrol.reflectiontool.ReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.template.graphic.texture.TextureCatalog;

final class TextureCatalogTest extends StandardTest {

  @Test
  void testCase_constantsOfTextureCatalog() {

    //verification
    final var textures = ReflectionTool.getStoredPublicStaticFieldValuesOfClass(TextureCatalog.class);

    for (final var t : textures) {

      expect(t).isOfType(Image.class);

      final var texture = (IImage) t;

      expect(texture.getWidth()).isEqualTo(16);
      expect(texture.getHeight()).isEqualTo(16);

      for (final var p : texture.getPixels()) {
        expect(p.hasFullAlphaValue());
      }
    }
  }
}
