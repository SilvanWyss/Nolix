package ch.nolix.template.graphic.texture;

import org.junit.jupiter.api.Test;

import ch.nolix.core.structurecontrol.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

final class TextureCatalogTest extends StandardTest {

  @Test
  void testCase_constantsOfTextureCatalog() {

    //verification
    final var textures = GlobalReflectionTool.getStoredPublicStaticFieldValuesOfClass(TextureCatalog.class);

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
