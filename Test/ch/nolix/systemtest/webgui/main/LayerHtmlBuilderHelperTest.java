package ch.nolix.systemtest.webgui.main;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.LayerHtmlBuilderHelper;

final class LayerHtmlBuilderHelperTest extends StandardTest {
  @Test
  void testCase_createIdHtmlAttributeForLayer() {
    //setup
    final var layer = new Layer();

    //execution
    final var result = LayerHtmlBuilderHelper.createIdHtmlAttributeForLayer(layer);

    //verification
    expect(result.hasName("id")).isTrue();
    expect(result.getValue().startsWith("i")).isTrue();
  }
}
