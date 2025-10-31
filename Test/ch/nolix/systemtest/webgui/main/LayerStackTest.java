package ch.nolix.systemtest.webgui.main;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.LayerStack;
import ch.nolix.system.webgui.main.WebGui;

final class LayerStackTest extends StandardTest {
  @Test
  void testCase_getOptionalStoredControlByInternalId_whenIsEmpty() {
    //setup
    final var control = new Label();
    final var testUnit = LayerStack.forWebGui(new WebGui());

    //setup verification
    expect(testUnit.isEmpty()).isTrue();

    //execution
    final var result = testUnit.getOptionalStoredControlByInternalId(control.getInternalId());

    //verification
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_getOptionalStoredControlByInternalId_whenContainsSuchAControl() {
    //setup
    final var control = new Label();
    final var testUnit = LayerStack.forWebGui(new WebGui());
    testUnit.pushLayerWithRootControl(control);

    //execution
    final var result = testUnit.getOptionalStoredControlByInternalId(control.getInternalId());

    //verification
    expect(result.get()).is(control); //NOSONAR: The current test case expects a non-empty result.
  }

  @Test
  void testCase_getStoredControls_whenIsEmpty() {
    //setup
    final var testUnit = LayerStack.forWebGui(new WebGui());

    //setup verification
    expect(testUnit.isEmpty()).isTrue();

    //execution
    final var result = testUnit.getStoredControls();

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_getStoredControls_whenContains1Control() {
    //setup
    final var testUnit = LayerStack.forWebGui(new WebGui());
    final var label = new Label();
    testUnit.pushLayerWithRootControl(label);

    //execution
    final var result = testUnit.getStoredControls();

    //verification
    expect(result).containsExactlyInSameOrder(label);
  }

  @Test
  void testCase_getStoredControls_whenContains4Control() {
    //setup
    final var testUnit = LayerStack.forWebGui(new WebGui());
    final var verticalStack = new VerticalStack();
    final var label1 = new Label();
    final var label2 = new Label();
    final var label3 = new Label();
    verticalStack.addControl(label1, label2, label3);
    testUnit.pushLayerWithRootControl(verticalStack);

    //execution
    final var result = testUnit.getStoredControls();

    //verification
    expect(result).containsExactlyInSameOrder(verticalStack, label1, label2, label3);
  }
}
