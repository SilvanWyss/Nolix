//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;

//class
public final class WebGuiTest extends StandardTest {

  //method
  @TestCase
  public void testCase_constructor() {

    //execution
    final var result = new WebGui();

    //verification
    expect(result.getTitle()).isEqualTo(WebGui.DEFAULT_TITLE);
    expect(result.getIcon()).isEqualTo(WebGui.DEFAULT_ICON);
    expect(result.getBackgroundColor()).isEqualTo(WebGui.DEFAULT_BACKGROUND_COLOR);
    expect(result.isEmpty());
    expect(result.getTokens()).isEmpty();
  }

  //method
  @TestCase
  public void testCase_reset() {

    //setup
    final var testUnit = new WebGui();
    testUnit.setTitle("my_title");
    testUnit.setIcon(MutableImage.withWidthAndHeightAndColor(100, 5, Color.BLUE));
    testUnit.pushLayer(new Layer());
    testUnit.addToken("my_token");

    //execution
    testUnit.reset();

    //verification
    expect(testUnit.getTitle()).isEqualTo(WebGui.DEFAULT_TITLE);
    expect(testUnit.getIcon()).isEqualTo(WebGui.DEFAULT_ICON);
    expect(testUnit.isEmpty());
    expect(testUnit.getTokens()).isEmpty();
  }
}
