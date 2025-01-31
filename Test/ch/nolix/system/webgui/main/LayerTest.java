package ch.nolix.system.webgui.main;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.systemapi.guiapi.contentalignmentproperty.ContentAlignment;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

final class LayerTest extends StandardTest {

  @Test
  void testCase_clear_whenIsEmpty() {

    //setup
    final var testUnit = new Layer();

    //setup verification
    expect(testUnit.isEmpty()).isTrue();

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_clear_whenContainsAny() {

    //setup
    final var testUnit = new Layer();
    testUnit.setRootControl(new Label());

    //setup verification
    expect(testUnit.containsAny()).isTrue();

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_removeSelfFromGui_whenDoesNotBelongToGui() {

    //setup
    final var testUnit = new Layer();

    //setup verification
    expect(testUnit.belongsToGui()).isFalse();

    //execution
    expectRunning(testUnit::removeSelfFromGui).doesNotThrowException();
  }

  @Test
  void testCase_removeSelfFromGui_whenBelongsToGui() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new Layer();
    webGui.pushLayer(testUnit);

    //setup verification
    expect(webGui.getStoredLayers()).contains(testUnit);
    expect(testUnit.belongsToGui()).isTrue();

    //execution
    testUnit.removeSelfFromGui();

    //verification
    expect(webGui.getStoredLayers().contains(testUnit)).isFalse();
    expect(testUnit.belongsToGui()).isFalse();
  }

  @Test
  void testCase_reset() {

    //setup
    final var testUnit = new Layer()
      .setId("id")
      .setRole(LayerRole.MAIN_LAYER)
      .setOpacity(0.5)
      .setBackgroundColor(X11ColorCatalog.AQUA)
      .setContentAlignment(ContentAlignment.BOTTOM_RIGHT)
      .setRootControl(new Label());

    //execution
    testUnit.reset();

    //verification
    expect(testUnit.hasId()).isFalse();
    expect(testUnit.hasRole()).isFalse();
    expect(testUnit.getOpacity()).isEqualTo(1.0);
    expect(testUnit.hasBackground()).isFalse();
    expect(testUnit.getContentAlignment()).is(ContentAlignment.TOP);
    expect(testUnit.isEmpty()).isTrue();
  }
}
