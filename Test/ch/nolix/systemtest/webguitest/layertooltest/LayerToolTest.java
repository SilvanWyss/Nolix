package ch.nolix.systemtest.webguitest.layertooltest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.layertool.LayerTool;
import ch.nolix.system.webgui.main.Layer;

final class LayerToolTest extends StandardTest {

  @Test
  void testCase() {

    //setup
    final var layer = new Layer();
    final var testUnit = new LayerTool();

    //execution
    final var result = testUnit.createIdHtmlAttributeForLayer(layer);

    //verification
    expect(result.hasName("id"));
    expect(result.getValue().startsWith("i"));
  }
}
