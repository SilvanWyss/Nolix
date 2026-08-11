/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.graphic.color;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.colorgradient.ColorGradient;
import ch.nolix.systemapi.graphic.graphicproperty.Direction;

/**
 * @author Silvan Wyss
 */
final class ColorGradientTest extends StandardTest {
  @Test
  void testCase_getSpecification() {
    // setup
    final var testUnit = ColorGradient.withDirectionAndColors(Direction.HORIZONTAL, X11ColorCatalog.BLACK,
      X11ColorCatalog.WHITE);

    // execute
    final var result = testUnit.getSpecification();

    // verify
    expect(result).hasStringRepresentation("ColorGradient(HORIZONTAL,0x000000,0xFFFFFF)");
  }

  @Test
  void testCase_fromSpecification_1A() {
    // setup
    final var specification = ImmutableNode.fromString("ColorGradient(0x000000,0xFFFFFF)");

    // execute
    final var result = ColorGradient.fromSpecification(specification);

    // verify
    expect(result.getDirection()).is(Direction.VERTICAL);
    expect(result.getColor1()).isEqualTo(X11ColorCatalog.BLACK);
    expect(result.getColor2()).isEqualTo(X11ColorCatalog.WHITE);
  }

  @Test
  void testCase_fromSpecification_1B() {
    // setup
    final var specification = ImmutableNode.fromString("ColorGradient(HORIZONTAL,0x000000,0xFFFFFF)");

    // execute
    final var result = ColorGradient.fromSpecification(specification);

    // verify
    expect(result.getDirection()).is(Direction.HORIZONTAL);
    expect(result.getColor1()).isEqualTo(X11ColorCatalog.BLACK);
    expect(result.getColor2()).isEqualTo(X11ColorCatalog.WHITE);
  }

  @Test
  void testCase_withColors() {
    // execute
    final var result = ColorGradient.withColors(X11ColorCatalog.BLACK, X11ColorCatalog.WHITE);

    // verify
    expect(result.getDirection()).is(Direction.VERTICAL);
    expect(result.getColor1()).is(X11ColorCatalog.BLACK);
    expect(result.getColor2()).is(X11ColorCatalog.WHITE);
  }

  @Test
  void testCase_withDirectionAndColors() {
    // execute
    final var result = ColorGradient.withDirectionAndColors(Direction.HORIZONTAL, X11ColorCatalog.BLACK,
      X11ColorCatalog.WHITE);

    // verify
    expect(result.getDirection()).is(Direction.HORIZONTAL);
    expect(result.getColor1()).is(X11ColorCatalog.BLACK);
    expect(result.getColor2()).is(X11ColorCatalog.WHITE);
  }
}
