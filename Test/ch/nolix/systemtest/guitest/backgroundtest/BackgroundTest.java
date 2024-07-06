//package declaration
package ch.nolix.systemtest.guitest.backgroundtest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.gui.background.Background;
import ch.nolix.systemapi.guiapi.backgroundapi.BackgroundType;

//class
final class BackgroundTest extends StandardTest {

  //method
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

  //method
  @Test
  void testCase_withColor() {

    //execution
    final var result = Background.withColor(Color.BLUE);

    //verification
    expect(result.getType()).is(BackgroundType.COLOR);
    expect(result.getColor()).is(Color.BLUE);
  }
}
