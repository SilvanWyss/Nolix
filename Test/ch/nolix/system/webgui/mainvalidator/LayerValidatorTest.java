package ch.nolix.system.webgui.mainvalidator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;

final class LayerValidatorTest extends StandardTest {

  @Test
  void testCase_assertBelongsToGui_whenTheGivenLayerDoesNotBelongToAGui() {

    //setup
    final var layer = new Layer();
    final var testUnit = new LayerValidator();

    //setup verification
    expect(layer.belongsToGui()).isFalse();

    //execution & verification
    expectRunning(() -> testUnit.assertBelongsToGui(layer))
      .throwsException()
      .ofType(ArgumentDoesNotBelongToParentException.class);
  }

  @Test
  void testCase_assertBelongsToGui_whenTheGivenLayerBelongsToAGui() {

    //setup
    final var layer = new Layer();
    new WebGui().pushLayer(layer);
    final var testUnit = new LayerValidator();

    //setup verification
    expect(layer.belongsToGui()).isTrue();

    //execution & verification
    expectRunning(() -> testUnit.assertBelongsToGui(layer)).doesNotThrowException();
  }

  @Test
  void testCase_assertDoesNotBelongsToGui_whenTheGivenLayerDoesNotBelongToAGui() {

    //setup
    final var layer = new Layer();
    final var testUnit = new LayerValidator();

    //setup verification
    expect(layer.belongsToGui()).isFalse();

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotBelongToGui(layer)).doesNotThrowException();
  }

  @Test
  void testCase_assertDoesNotBelongToGui_whenTheGivenLayerBelongsToAGui() {

    //setup
    final var layer = new Layer();
    new WebGui().pushLayer(layer);
    final var testUnit = new LayerValidator();

    //setup verification
    expect(layer.belongsToGui()).isTrue();

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotBelongToGui(layer))
      .throwsException()
      .ofType(ArgumentBelongsToParentException.class);
  }
}
