package ch.nolix.systemtest.graphictest.colortest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.ColorGradient;
import ch.nolix.systemapi.guiapi.canvasapi.DirectionInCanvas;

final class ColorGradientTest extends StandardTest {

  @Test
  void testCase_getSpecification() {

    //setup
    final var testUnit = ColorGradient.withDirectionAndColors(DirectionInCanvas.HORIZONTAL, Color.BLACK, Color.WHITE);

    //execution
    final var result = testUnit.getSpecification();

    //verification
    expect(result).hasStringRepresentation("ColorGradient(HORIZONTAL,0x000000,0xFFFFFF)");
  }

  @Test
  void testCase_fromSpecification_1A() {

    //setup
    final var specification = Node.fromString("ColorGradient(0x000000,0xFFFFFF)");

    //execution
    final var result = ColorGradient.fromSpecification(specification);

    //verification
    expect(result.getDirection()).is(DirectionInCanvas.VERTICAL);
    expect(result.getColor1()).isEqualTo(Color.BLACK);
    expect(result.getColor2()).isEqualTo(Color.WHITE);
  }

  @Test
  void testCase_fromSpecification_1B() {

    //setup
    final var specification = Node.fromString("ColorGradient(HORIZONTAL,0x000000,0xFFFFFF)");

    //execution
    final var result = ColorGradient.fromSpecification(specification);

    //verification
    expect(result.getDirection()).is(DirectionInCanvas.HORIZONTAL);
    expect(result.getColor1()).isEqualTo(Color.BLACK);
    expect(result.getColor2()).isEqualTo(Color.WHITE);
  }

  @Test
  void testCase_withColors() {

    //execution
    final var result = ColorGradient.withColors(Color.BLACK, Color.WHITE);

    //verification
    expect(result.getDirection()).is(DirectionInCanvas.VERTICAL);
    expect(result.getColor1()).is(Color.BLACK);
    expect(result.getColor2()).is(Color.WHITE);
  }

  @Test
  void testCase_withDirectionAndColors() {

    //execution
    final var result = ColorGradient.withDirectionAndColors(DirectionInCanvas.HORIZONTAL, Color.BLACK, Color.WHITE);

    //verification
    expect(result.getDirection()).is(DirectionInCanvas.HORIZONTAL);
    expect(result.getColor1()).is(Color.BLACK);
    expect(result.getColor2()).is(Color.WHITE);
  }
}
