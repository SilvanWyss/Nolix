package ch.nolix.system.graphic.image;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalog;

final class MutableImageTest extends StandardTest {

  @Test
  void testCase_constructor() {

    //parameter definition
    final var width = 100;
    final var height = 50;
    final var color = X11ColorCatalog.BLUE;

    //execution
    final var testUnit = MutableImage.withWidthAndHeightAndColor(width, height, color);

    //verification part 1
    expect(testUnit.getWidth()).isEqualTo(width);
    expect(testUnit.getHeight()).isEqualTo(height);

    //verification part 2
    for (var i = 1; i <= width; i++) {
      for (var j = 1; j <= height; j++) {
        expect(testUnit.getPixel(i, j)).isEqualTo(color);
      }
    }
  }

  @Test
  void testCase_getBottomLeftPixel() {

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, X11ColorCatalog.WHITE);
    testUnit.setPixel(1, 50, X11ColorCatalog.RED);

    //execution
    final var result = testUnit.getBottomLeftPixel();

    //verification
    expect(result).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_getBottomRightPixel() {

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, X11ColorCatalog.WHITE);
    testUnit.setPixel(100, 50, X11ColorCatalog.RED);

    //execution
    final var result = testUnit.getBottomRightPixel();

    //verification
    expect(result).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_getCopy_whenOriginIsChangedAfterwards() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, X11ColorCatalog.YELLOW)
      .setPixel(1, 2, X11ColorCatalog.RED)
      .setPixel(2, 1, X11ColorCatalog.GREEN)
      .setPixel(2, 2, X11ColorCatalog.BLUE);

    //execution
    final var result = testUnit.getCopy();
    testUnit
      .setPixel(1, 1, X11ColorCatalog.BLACK)
      .setPixel(1, 2, X11ColorCatalog.BLACK)
      .setPixel(2, 1, X11ColorCatalog.BLACK)
      .setPixel(2, 2, X11ColorCatalog.BLACK);

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(1, 2)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(2, 1)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(2, 2)).isEqualTo(X11ColorCatalog.BLUE);
  }

  @Test
  void testCase_getSection() {

    //setup
    final var testUnit = //
    MutableImage
      .withWidthAndHeightAndWhiteColor(4, 4)
      .setPixel(1, 1, X11ColorCatalog.RED)
      .setPixel(1, 2, X11ColorCatalog.ORANGE)
      .setPixel(1, 3, X11ColorCatalog.PINK)
      .setPixel(1, 4, X11ColorCatalog.PURPLE)
      .setPixel(2, 1, X11ColorCatalog.BLUE)
      .setPixel(2, 2, X11ColorCatalog.CYAN)
      .setPixel(2, 3, X11ColorCatalog.TURQUOISE)
      .setPixel(2, 4, X11ColorCatalog.GREEN)
      .setPixel(3, 1, X11ColorCatalog.BLACK)
      .setPixel(3, 2, X11ColorCatalog.BROWN)
      .setPixel(3, 3, X11ColorCatalog.GREY)
      .setPixel(3, 4, X11ColorCatalog.WHITE)
      .setPixel(4, 1, X11ColorCatalog.YELLOW)
      .setPixel(4, 2, X11ColorCatalog.GOLD)
      .setPixel(4, 3, X11ColorCatalog.BEIGE)
      .setPixel(4, 4, X11ColorCatalog.YELLOW_GREEN);

    //execution
    final var result = testUnit.getSection(2, 2, 2, 2);

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(X11ColorCatalog.CYAN);
    expect(result.getPixel(1, 2)).isEqualTo(X11ColorCatalog.TURQUOISE);
    expect(result.getPixel(2, 1)).isEqualTo(X11ColorCatalog.BROWN);
    expect(result.getPixel(2, 2)).isEqualTo(X11ColorCatalog.GREY);
  }

  @Test
  void testCase_getTopLeftPixel() {

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, X11ColorCatalog.WHITE);
    testUnit.setPixel(1, 1, X11ColorCatalog.RED);

    //execution
    final var result = testUnit.getTopLeftPixel();

    //verification
    expect(result).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_getTopRightPixel() {

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, X11ColorCatalog.WHITE);
    testUnit.setPixel(100, 1, X11ColorCatalog.RED);

    //execution
    final var result = testUnit.getTopRightPixel();

    //verification
    expect(result).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_reset() {

    //parameter definition
    final var width = 100;
    final var height = 50;

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndWhiteColor(width, height);
    for (var i = 1; i <= width; i++) {
      testUnit.setPixel(i, 1, X11ColorCatalog.RED);
    }

    //execution
    testUnit.reset();

    //verification part 1
    expect(testUnit.getWidth()).isEqualTo(width);
    expect(testUnit.getHeight()).isEqualTo(height);

    //verification part 2
    for (var i = 1; i <= width; i++) {
      for (var j = 1; j <= height; j++) {
        expect(testUnit.getPixel(i, j)).isEqualTo(X11ColorCatalog.WHITE);
      }
    }
  }

  @Test
  void testCase_toLeftRotatedImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, X11ColorCatalog.YELLOW)
      .setPixel(1, 2, X11ColorCatalog.RED)
      .setPixel(2, 1, X11ColorCatalog.GREEN)
      .setPixel(2, 2, X11ColorCatalog.BLUE);

    //execution
    final var result = testUnit.toLeftRotatedImage();

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(1, 2)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(2, 1)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(2, 2)).isEqualTo(X11ColorCatalog.RED);
  }

  @Test
  void testCase_toRepeatedImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, X11ColorCatalog.YELLOW)
      .setPixel(1, 2, X11ColorCatalog.RED)
      .setPixel(2, 1, X11ColorCatalog.GREEN)
      .setPixel(2, 2, X11ColorCatalog.BLUE);

    //execution
    final var result = testUnit.toRepeatedImage(4, 4);

    //verification
    expect(result.getWidth()).isEqualTo(4);
    expect(result.getHeight()).isEqualTo(4);
    expect(result.getPixel(1, 1)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(1, 2)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(1, 3)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(1, 4)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(2, 1)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(2, 2)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(2, 3)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(2, 4)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(3, 1)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(3, 2)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(3, 3)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(3, 4)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(4, 1)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(4, 2)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(4, 3)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(4, 4)).isEqualTo(X11ColorCatalog.BLUE);
  }

  @Test
  void testCase_toRightRotatedImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, X11ColorCatalog.YELLOW)
      .setPixel(1, 2, X11ColorCatalog.RED)
      .setPixel(2, 1, X11ColorCatalog.GREEN)
      .setPixel(2, 2, X11ColorCatalog.BLUE);

    //execution
    final var result = testUnit.toRightRotatedImage();

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(1, 2)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(2, 1)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(2, 2)).isEqualTo(X11ColorCatalog.GREEN);
  }

  @Test
  void testCase_toScaledImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, X11ColorCatalog.YELLOW)
      .setPixel(1, 2, X11ColorCatalog.RED)
      .setPixel(2, 1, X11ColorCatalog.GREEN)
      .setPixel(2, 2, X11ColorCatalog.BLUE);

    //execution
    final var result = testUnit.toScaledImage(2.0);

    //verification
    expect(result.getWidth()).isEqualTo(4);
    expect(result.getHeight()).isEqualTo(4);
    expect(result.getPixel(1, 1)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(1, 2)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(1, 3)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(1, 4)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(2, 1)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(2, 2)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(result.getPixel(2, 3)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(2, 4)).isEqualTo(X11ColorCatalog.RED);
    expect(result.getPixel(3, 1)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(3, 2)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(3, 3)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(3, 4)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(4, 1)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(4, 2)).isEqualTo(X11ColorCatalog.GREEN);
    expect(result.getPixel(4, 3)).isEqualTo(X11ColorCatalog.BLUE);
    expect(result.getPixel(4, 4)).isEqualTo(X11ColorCatalog.BLUE);
  }

  @Test
  void testCase_withAlphaValue() {

    //setup
    final var testUnit = //
    MutableImage
      .withWidthAndHeightAndWhiteColor(3, 2)
      .setPixel(1, 1, X11ColorCatalog.YELLOW)
      .setPixel(1, 2, X11ColorCatalog.RED)
      .setPixel(2, 1, X11ColorCatalog.GREEN)
      .setPixel(2, 2, X11ColorCatalog.BLUE)
      .setPixel(3, 1, X11ColorCatalog.ORANGE)
      .setPixel(3, 2, X11ColorCatalog.VIOLET);

    //execution
    final var result = testUnit.withAlphaValue(0.25);

    //verification part 1: Verifies testUnit.
    expect(testUnit.getPixel(1, 1)).isEqualTo(X11ColorCatalog.YELLOW);
    expect(testUnit.getPixel(1, 2)).isEqualTo(X11ColorCatalog.RED);
    expect(testUnit.getPixel(2, 1)).isEqualTo(X11ColorCatalog.GREEN);
    expect(testUnit.getPixel(2, 2)).isEqualTo(X11ColorCatalog.BLUE);
    expect(testUnit.getPixel(3, 1)).isEqualTo(X11ColorCatalog.ORANGE);
    expect(testUnit.getPixel(3, 2)).isEqualTo(X11ColorCatalog.VIOLET);

    //verification part 2: Verifies result.
    expect(result.getWidth()).isEqualTo(3);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(X11ColorCatalog.YELLOW.withAlphaValue(63));
    expect(result.getPixel(1, 2)).isEqualTo(X11ColorCatalog.RED.withAlphaValue(63));
    expect(result.getPixel(2, 1)).isEqualTo(X11ColorCatalog.GREEN.withAlphaValue(63));
    expect(result.getPixel(2, 2)).isEqualTo(X11ColorCatalog.BLUE.withAlphaValue(63));
    expect(result.getPixel(3, 1)).isEqualTo(X11ColorCatalog.ORANGE.withAlphaValue(63));
    expect(result.getPixel(3, 2)).isEqualTo(X11ColorCatalog.VIOLET.withAlphaValue(63));
  }
}
