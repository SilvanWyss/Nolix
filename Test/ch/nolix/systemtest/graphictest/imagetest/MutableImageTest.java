package ch.nolix.systemtest.graphictest.imagetest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.MutableImage;

final class MutableImageTest extends StandardTest {

  @Test
  void testCase_constructor() {

    //parameter definition
    final var width = 100;
    final var height = 50;
    final var color = Color.BLUE;

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
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, Color.WHITE);
    testUnit.setPixel(1, 50, Color.RED);

    //execution
    final var result = testUnit.getBottomLeftPixel();

    //verification
    expect(result).isEqualTo(Color.RED);
  }

  @Test
  void testCase_getBottomRightPixel() {

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, Color.WHITE);
    testUnit.setPixel(100, 50, Color.RED);

    //execution
    final var result = testUnit.getBottomRightPixel();

    //verification
    expect(result).isEqualTo(Color.RED);
  }

  @Test
  void testCase_getCopy_whenOriginIsChangedAfterwards() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, Color.YELLOW)
      .setPixel(1, 2, Color.RED)
      .setPixel(2, 1, Color.GREEN)
      .setPixel(2, 2, Color.BLUE);

    //execution
    final var result = testUnit.getCopy();
    testUnit
      .setPixel(1, 1, Color.BLACK)
      .setPixel(1, 2, Color.BLACK)
      .setPixel(2, 1, Color.BLACK)
      .setPixel(2, 2, Color.BLACK);

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(1, 2)).isEqualTo(Color.RED);
    expect(result.getPixel(2, 1)).isEqualTo(Color.GREEN);
    expect(result.getPixel(2, 2)).isEqualTo(Color.BLUE);
  }

  @Test
  void testCase_getSection() {

    //setup
    final var testUnit = //
    MutableImage
      .withWidthAndHeightAndWhiteColor(4, 4)
      .setPixel(1, 1, Color.RED)
      .setPixel(1, 2, Color.ORANGE)
      .setPixel(1, 3, Color.PINK)
      .setPixel(1, 4, Color.PURPLE)
      .setPixel(2, 1, Color.BLUE)
      .setPixel(2, 2, Color.CYAN)
      .setPixel(2, 3, Color.TURQUOISE)
      .setPixel(2, 4, Color.GREEN)
      .setPixel(3, 1, Color.BLACK)
      .setPixel(3, 2, Color.BROWN)
      .setPixel(3, 3, Color.GREY)
      .setPixel(3, 4, Color.WHITE)
      .setPixel(4, 1, Color.YELLOW)
      .setPixel(4, 2, Color.GOLD)
      .setPixel(4, 3, Color.BEIGE)
      .setPixel(4, 4, Color.YELLOW_GREEN);

    //execution
    final var result = testUnit.getSection(2, 2, 2, 2);

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(Color.CYAN);
    expect(result.getPixel(1, 2)).isEqualTo(Color.TURQUOISE);
    expect(result.getPixel(2, 1)).isEqualTo(Color.BROWN);
    expect(result.getPixel(2, 2)).isEqualTo(Color.GREY);
  }

  @Test
  void testCase_getTopLeftPixel() {

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, Color.WHITE);
    testUnit.setPixel(1, 1, Color.RED);

    //execution
    final var result = testUnit.getTopLeftPixel();

    //verification
    expect(result).isEqualTo(Color.RED);
  }

  @Test
  void testCase_getTopRightPixel() {

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndColor(100, 50, Color.WHITE);
    testUnit.setPixel(100, 1, Color.RED);

    //execution
    final var result = testUnit.getTopRightPixel();

    //verification
    expect(result).isEqualTo(Color.RED);
  }

  @Test
  void testCase_reset() {

    //parameter definition
    final var width = 100;
    final var height = 50;

    //setup
    final var testUnit = MutableImage.withWidthAndHeightAndWhiteColor(width, height);
    for (var i = 1; i <= width; i++) {
      testUnit.setPixel(i, 1, Color.RED);
    }

    //execution
    testUnit.reset();

    //verification part 1
    expect(testUnit.getWidth()).isEqualTo(width);
    expect(testUnit.getHeight()).isEqualTo(height);

    //verification part 2
    for (var i = 1; i <= width; i++) {
      for (var j = 1; j <= height; j++) {
        expect(testUnit.getPixel(i, j)).isEqualTo(Color.WHITE);
      }
    }
  }

  @Test
  void testCase_toLeftRotatedImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, Color.YELLOW)
      .setPixel(1, 2, Color.RED)
      .setPixel(2, 1, Color.GREEN)
      .setPixel(2, 2, Color.BLUE);

    //execution
    final var result = testUnit.toLeftRotatedImage();

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(Color.GREEN);
    expect(result.getPixel(1, 2)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(2, 1)).isEqualTo(Color.BLUE);
    expect(result.getPixel(2, 2)).isEqualTo(Color.RED);
  }

  @Test
  void testCase_toRepeatedImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, Color.YELLOW)
      .setPixel(1, 2, Color.RED)
      .setPixel(2, 1, Color.GREEN)
      .setPixel(2, 2, Color.BLUE);

    //execution
    final var result = testUnit.toRepeatedImage(4, 4);

    //verification
    expect(result.getWidth()).isEqualTo(4);
    expect(result.getHeight()).isEqualTo(4);
    expect(result.getPixel(1, 1)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(1, 2)).isEqualTo(Color.RED);
    expect(result.getPixel(1, 3)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(1, 4)).isEqualTo(Color.RED);
    expect(result.getPixel(2, 1)).isEqualTo(Color.GREEN);
    expect(result.getPixel(2, 2)).isEqualTo(Color.BLUE);
    expect(result.getPixel(2, 3)).isEqualTo(Color.GREEN);
    expect(result.getPixel(2, 4)).isEqualTo(Color.BLUE);
    expect(result.getPixel(3, 1)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(3, 2)).isEqualTo(Color.RED);
    expect(result.getPixel(3, 3)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(3, 4)).isEqualTo(Color.RED);
    expect(result.getPixel(4, 1)).isEqualTo(Color.GREEN);
    expect(result.getPixel(4, 2)).isEqualTo(Color.BLUE);
    expect(result.getPixel(4, 3)).isEqualTo(Color.GREEN);
    expect(result.getPixel(4, 4)).isEqualTo(Color.BLUE);
  }

  @Test
  void testCase_toRightRotatedImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, Color.YELLOW)
      .setPixel(1, 2, Color.RED)
      .setPixel(2, 1, Color.GREEN)
      .setPixel(2, 2, Color.BLUE);

    //execution
    final var result = testUnit.toRightRotatedImage();

    //verification
    expect(result.getWidth()).isEqualTo(2);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(Color.RED);
    expect(result.getPixel(1, 2)).isEqualTo(Color.BLUE);
    expect(result.getPixel(2, 1)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(2, 2)).isEqualTo(Color.GREEN);
  }

  @Test
  void testCase_toScaledImage() {

    //setup
    final var testUnit = MutableImage
      .withWidthAndHeightAndWhiteColor(2, 2)
      .setPixel(1, 1, Color.YELLOW)
      .setPixel(1, 2, Color.RED)
      .setPixel(2, 1, Color.GREEN)
      .setPixel(2, 2, Color.BLUE);

    //execution
    final var result = testUnit.toScaledImage(2.0);

    //verification
    expect(result.getWidth()).isEqualTo(4);
    expect(result.getHeight()).isEqualTo(4);
    expect(result.getPixel(1, 1)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(1, 2)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(1, 3)).isEqualTo(Color.RED);
    expect(result.getPixel(1, 4)).isEqualTo(Color.RED);
    expect(result.getPixel(2, 1)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(2, 2)).isEqualTo(Color.YELLOW);
    expect(result.getPixel(2, 3)).isEqualTo(Color.RED);
    expect(result.getPixel(2, 4)).isEqualTo(Color.RED);
    expect(result.getPixel(3, 1)).isEqualTo(Color.GREEN);
    expect(result.getPixel(3, 2)).isEqualTo(Color.GREEN);
    expect(result.getPixel(3, 3)).isEqualTo(Color.BLUE);
    expect(result.getPixel(3, 4)).isEqualTo(Color.BLUE);
    expect(result.getPixel(4, 1)).isEqualTo(Color.GREEN);
    expect(result.getPixel(4, 2)).isEqualTo(Color.GREEN);
    expect(result.getPixel(4, 3)).isEqualTo(Color.BLUE);
    expect(result.getPixel(4, 4)).isEqualTo(Color.BLUE);
  }

  @Test
  void testCase_withAlphaValue() {

    //setup
    final var testUnit = //
    MutableImage
      .withWidthAndHeightAndWhiteColor(3, 2)
      .setPixel(1, 1, Color.YELLOW)
      .setPixel(1, 2, Color.RED)
      .setPixel(2, 1, Color.GREEN)
      .setPixel(2, 2, Color.BLUE)
      .setPixel(3, 1, Color.ORANGE)
      .setPixel(3, 2, Color.VIOLET);

    //execution
    final var result = testUnit.withAlphaValue(0.25);

    //verification part 1: Verifies testUnit.
    expect(testUnit.getPixel(1, 1)).isEqualTo(Color.YELLOW);
    expect(testUnit.getPixel(1, 2)).isEqualTo(Color.RED);
    expect(testUnit.getPixel(2, 1)).isEqualTo(Color.GREEN);
    expect(testUnit.getPixel(2, 2)).isEqualTo(Color.BLUE);
    expect(testUnit.getPixel(3, 1)).isEqualTo(Color.ORANGE);
    expect(testUnit.getPixel(3, 2)).isEqualTo(Color.VIOLET);

    //verification part 2: Verifies result.
    expect(result.getWidth()).isEqualTo(3);
    expect(result.getHeight()).isEqualTo(2);
    expect(result.getPixel(1, 1)).isEqualTo(Color.YELLOW.withAlphaValue(63));
    expect(result.getPixel(1, 2)).isEqualTo(Color.RED.withAlphaValue(63));
    expect(result.getPixel(2, 1)).isEqualTo(Color.GREEN.withAlphaValue(63));
    expect(result.getPixel(2, 2)).isEqualTo(Color.BLUE.withAlphaValue(63));
    expect(result.getPixel(3, 1)).isEqualTo(Color.ORANGE.withAlphaValue(63));
    expect(result.getPixel(3, 2)).isEqualTo(Color.VIOLET.withAlphaValue(63));
  }
}
