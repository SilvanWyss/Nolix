package ch.nolix.systemtest.graphic.color;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.ColorGradient;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.systemapi.graphicapi.imageproperty.Alignment;

final class ColorGradientTest extends StandardTest {

  @Test
  void testCase_getSpecification() {

    //setup
    final var testUnit = ColorGradient.withDirectionAndColors(Alignment.HORIZONTAL, X11ColorCatalog.BLACK,
      X11ColorCatalog.WHITE);

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
    expect(result.getDirection()).is(Alignment.VERTICAL);
    expect(result.getColor1()).isEqualTo(X11ColorCatalog.BLACK);
    expect(result.getColor2()).isEqualTo(X11ColorCatalog.WHITE);
  }

  @Test
  void testCase_fromSpecification_1B() {

    //setup
    final var specification = Node.fromString("ColorGradient(HORIZONTAL,0x000000,0xFFFFFF)");

    //execution
    final var result = ColorGradient.fromSpecification(specification);

    //verification
    expect(result.getDirection()).is(Alignment.HORIZONTAL);
    expect(result.getColor1()).isEqualTo(X11ColorCatalog.BLACK);
    expect(result.getColor2()).isEqualTo(X11ColorCatalog.WHITE);
  }

  @Test
  void testCase_withColors() {

    //execution
    final var result = ColorGradient.withColors(X11ColorCatalog.BLACK, X11ColorCatalog.WHITE);

    //verification
    expect(result.getDirection()).is(Alignment.VERTICAL);
    expect(result.getColor1()).is(X11ColorCatalog.BLACK);
    expect(result.getColor2()).is(X11ColorCatalog.WHITE);
  }

  @Test
  void testCase_withDirectionAndColors() {

    //execution
    final var result = ColorGradient.withDirectionAndColors(Alignment.HORIZONTAL, X11ColorCatalog.BLACK,
      X11ColorCatalog.WHITE);

    //verification
    expect(result.getDirection()).is(Alignment.HORIZONTAL);
    expect(result.getColor1()).is(X11ColorCatalog.BLACK);
    expect(result.getColor2()).is(X11ColorCatalog.WHITE);
  }
}
