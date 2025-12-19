package ch.nolix.template.graphic.texture;

import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.graphic.image.MutableImage;

/**
 * @author Silvan Wyss
 */
public final class TextureCreator {
  private TextureCreator() {
  }

  public static Image createConcreteTexture() {
    final var texture = MutableImage.withWidthAndHeightAndWhiteColor(16, 16);

    final var color1 = Color.fromLong(0x808080);
    final var color2 = Color.fromLong(0xC0C0C0);
    final var color3 = Color.fromLong(0xA0A0A0);

    for (var x = 1; x <= texture.getWidth(); x++) {
      for (var y = 1; y <= texture.getHeight(); y++) {
        if ((y + 1) * (x * y - y % 3) % 7 == (x * y - x % 5 + y % 2) % 11) {
          texture.setPixel(x, y, color1);
        } else if ((x * x + y * y) % 11 == (x * y) % 13) {
          texture.setPixel(x, y, color2);
        } else {
          texture.setPixel(x, y, color3);
        }
      }
    }

    return texture.toImmutableImage();
  }

  public static Image createFirWoodTexture() {
    final var texture = MutableImage.withWidthAndHeightAndWhiteColor(16, 16);

    final var mainColor = Color.fromLong(0xE0D0B0);
    final var darkerStripeColor = Color.fromLong(0xC0B080);
    final var knotholeColor = Color.fromLong(0x807060);

    for (var x = 1; x <= texture.getWidth(); x++) {
      for (var y = 1; y <= texture.getHeight(); y++) {
        if ((x + y % 2) % 10 == 2 && y % 5 == 0) {
          texture.setPixel(x, y, knotholeColor);
        } else if ((y + (x / (10 + y / 10))) % (3 + y / 10) == 0) {
          texture.setPixel(x, y, darkerStripeColor);
        } else {
          texture.setPixel(x, y, mainColor);
        }
      }
    }

    return texture.toImmutableImage();
  }

  public static Image createJuteTexture() {
    final var texture = MutableImage.withWidthAndHeightAndWhiteColor(16, 16);

    final var color1 = Color.fromLong(0xC0C0A0);
    final var color2 = Color.fromLong(0x808060);
    final var color3 = Color.fromLong(0xA0A080);

    for (var x = 1; x <= texture.getWidth(); x++) {
      for (var y = 1; y <= texture.getHeight(); y++) {
        if ((x * y) % 3 == (x + y) % 2) {
          texture.setPixel(x, y, color1);
        } else if ((x * x + y * y) % 5 == (x * y) % 7) {
          texture.setPixel(x, y, color2);
        } else {
          texture.setPixel(x, y, color3);
        }
      }
    }

    return texture.toImmutableImage();
  }

  public static Image createParchmentTexture() {
    final var texture = MutableImage.withWidthAndHeightAndWhiteColor(16, 16);

    final var color1 = Color.fromLong(0xEFEFCF);
    final var color2 = Color.fromLong(0xCFAF4F);
    final var color3 = Color.fromLong(0x4F2F00);

    for (var x = 1; x <= texture.getWidth(); x++) {
      for (var y = 1; y <= texture.getHeight(); y++) {
        if ((x + (x - y) % 3) % 4 != 0 && (y * y) % x < 5) {
          texture.setPixel(x, y, color1);
        } else if ((x * x) % y != 0) {
          texture.setPixel(x, y, color2);
        } else {
          texture.setPixel(x, y, color3);
        }
      }
    }

    return texture.toImmutableImage();
  }

  public static Image createWhiteMarbleTexture() {
    final var texture = MutableImage.withWidthAndHeightAndWhiteColor(16, 16);

    final var color1 = Color.fromLong(0x282820);
    final var color2 = Color.fromLong(0x888880);
    final var color3 = Color.fromLong(0xF8F8F0);

    for (var x = 1; x <= texture.getWidth(); x++) {
      for (var y = 1; y <= texture.getHeight(); y++) {
        if (x == (y / 2) + (x / 5) || x == (y / 4) + (x / 4) + 8 || (x % 11 == (y - 2) % 7)) {
          texture.setPixel(x, y, color1);
        } else if (((texture.getWidth() - x) % 5) == (y % 3)) {
          texture.setPixel(x, y, color2);
        } else {
          texture.setPixel(x, y, color3);
        }
      }
    }

    return texture.toImmutableImage();
  }
}
