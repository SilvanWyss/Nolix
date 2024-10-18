package ch.nolix.templatetest.graphictest.texturetest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.template.graphic.texture.TextureCatalogue;

final class TextureCatalogueTest extends StandardTest {

  @Test
  void testCase_constantsOfTextureCatalogue() {

    //verification
    final var textures = GlobalReflectionTool.getPublicStaticFieldValuesOfClass(TextureCatalogue.class);
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
