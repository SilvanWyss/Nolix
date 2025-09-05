package ch.nolix.systemtest.gui.background;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.background.Background;
import ch.nolix.systemapi.gui.background.BackgroundType;

final class BackgroundTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(strings = {
  "Background(Color(0x010203))",
  "Background(ColorGradient(HORIZONTAL,0x010000,0x020000))",
  "Background(Transparency)"
  })
  void testCase_fromSpecification(final String backgroundSpecificationAsString) {
    //setup
    final var backgroundSpecification = Node.fromString(backgroundSpecificationAsString);

    //execute
    final var result = Background.fromSpecification(backgroundSpecification);

    //verification
    expect(result.getSpecification()).isEqualTo(backgroundSpecification);
  }

  @Test
  void testCase_withColor() {
    //execution
    final var result = Background.withColor(X11ColorCatalog.BLUE);

    //verification
    expect(result.getType()).is(BackgroundType.COLOR);
    expect(result.getColor()).is(X11ColorCatalog.BLUE);
  }
}
