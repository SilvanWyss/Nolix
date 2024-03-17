//package declaration
package ch.nolix.systemtest.guitest.backgroundtest;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.gui.background.Background;
import ch.nolix.systemapi.guiapi.backgroundapi.BackgroundType;

//class
public final class BackgroundTest extends StandardTest {

  //method
  @TestCase
  public void testCase_withColor() {

    //execution
    final var result = Background.withColor(Color.BLUE);

    //verification
    expect(result.getType()).is(BackgroundType.COLOR);
    expect(result.getColor()).is(Color.BLUE);
  }
}
