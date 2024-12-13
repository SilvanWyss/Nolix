package ch.nolix.templatetest.webguitest.dialogtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;
import ch.nolix.template.webgui.dialog.ShowValueDialogBuilder;

final class ShowValueDialogBuilderTest extends StandardTest {

  @Test
  void testCase_build() {

    //setup
    final var testUnit = new ShowValueDialogBuilder();

    //execution
    final var result = testUnit.build();

    //verification
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
  }

  @Test
  void testCase_build_thenAddToWebGui_thenClickConfirmButton() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new ShowValueDialogBuilder();

    //execution part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

    //execution part 2
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

    //verification
    expect(result.belongsToGui()).isFalse();
  }

  private boolean isConfirmButton(final IControl<?, ?> control) {

    if (control instanceof final IButton button) {
      return (button.getRole() == ButtonRole.CONFIRM_BUTTON);
    }

    return false;
  }
}
