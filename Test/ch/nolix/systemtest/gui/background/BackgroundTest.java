/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.gui.background;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.background.Background;
import ch.nolix.systemapi.gui.background.BackgroundType;

/**
 * @author Silvan Wyss
 */
final class BackgroundTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(strings = {
  "Background(Color(0x010203))",
  "Background(ColorGradient(HORIZONTAL,0x010000,0x020000))",
  "Background(Transparency)"
  })
  void testCase_fromSpecification(final String backgroundSpecificationAsString) {
    // setup
    final var backgroundSpecification = ImmutableNode.fromString(backgroundSpecificationAsString);

     // execute
    final var result = Background.fromSpecification(backgroundSpecification);

    // verify
    expect(result.getSpecification()).isEqualTo(backgroundSpecification);
  }

  @Test
  void testCase_withColor() {
    // execute
    final var result = Background.withColor(X11ColorCatalog.BLUE);

    // verify
    expect(result.getType()).is(BackgroundType.COLOR);
    expect(result.getColor()).is(X11ColorCatalog.BLUE);
  }
}
