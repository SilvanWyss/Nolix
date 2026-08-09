/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.templatetest.graphic.texture;

import org.junit.jupiter.api.Test;

import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.image.ImmutableImage;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.template.graphic.texture.TextureCatalog;

/**
 * @author Silvan Wyss
 */
final class TextureCatalogTest extends StandardTest {
  @Test
  void testCase_constantsOfTextureCatalog() {
    // verify
    final var textures = ReflectionTool.getStoredPublicStaticFieldValuesOfClass(TextureCatalog.class);

    for (final var t : textures) {
      expect(t).isOfType(ImmutableImage.class);

      final var texture = (Image) t;

      expect(texture.getWidth()).isEqualTo(16);
      expect(texture.getHeight()).isEqualTo(16);

      for (final var p : texture.getPixels()) {
        expect(p.hasFullAlphaValue());
      }
    }
  }
}
