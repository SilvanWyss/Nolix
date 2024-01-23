//package declaration
package ch.nolix.templatetest.graphictest.texturetest;

//own imports
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.template.graphic.texture.TextureCatalogue;

//class
public final class TextureCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_constantsOfTextureCatalogue() {

    //verification
    final var textures = GlobalClassTool.getPublicStaticFieldValuesOfClass(TextureCatalogue.class);
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
