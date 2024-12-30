package ch.nolix.systemtest.webguitest.maintest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalogue;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;

final class WebGuiTest extends StandardTest {

  @Test
  void testCase_constructor() {

    //execution
    final var result = new WebGui();

    //verification
    expect(result.getTitle()).isEqualTo(WebGui.DEFAULT_TITLE);
    expect(result.getIcon()).isEqualTo(WebGui.DEFAULT_ICON);
    expect(result.getBackgroundColor()).isEqualTo(WebGui.DEFAULT_BACKGROUND_COLOR);
    expect(result.isEmpty()).isTrue();
    expect(result.getTokens()).isEmpty();
  }

  @Test
  void testCase_reset() {

    //setup
    final var testUnit = new WebGui();
    testUnit.setTitle("my_title");
    testUnit.setIcon(MutableImage.withWidthAndHeightAndColor(100, 5, X11ColorCatalogue.BLUE));
    testUnit.pushLayer(new Layer());
    testUnit.addToken("my_token");

    //execution
    testUnit.reset();

    //verification
    expect(testUnit.getTitle()).isEqualTo(WebGui.DEFAULT_TITLE);
    expect(testUnit.getIcon()).isEqualTo(WebGui.DEFAULT_ICON);
    expect(testUnit.isEmpty()).isTrue();
    expect(testUnit.getTokens()).isEmpty();
  }
}
