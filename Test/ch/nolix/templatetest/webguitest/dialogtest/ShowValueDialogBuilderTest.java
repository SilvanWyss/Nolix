//package declaration
package ch.nolix.templatetest.webguitest.dialogtest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;
import ch.nolix.template.webgui.dialog.ShowValueDialogBuilder;

//class
final class ShowValueDialogBuilderTest extends StandardTest {

  //method
  @Test
  void testCase_build() {

    //setup
    final var testUnit = new ShowValueDialogBuilder();

    //execution
    final var result = testUnit.build();

    //verification part 1
    final var expectedStructure = //
    new Layer()
      .setRootControl(
        new VerticalStack()
          .addControl(
            new Label(),
            new HorizontalStack()
              .addControl(
                new Label(),
                new Button()),
            new Button()));
    expect(result.getStructureSpecification())
      .hasSameStringRepresentationAs(expectedStructure.getStructureSpecification());

    //verification part 2
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
  }

  //method
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
    expectNot(result.belongsToGui());
  }

  //method
  private boolean isConfirmButton(final IControl<?, ?> control) {

    if (control instanceof IButton button) {
      return (button.getRole() == ButtonRole.CONFIRM_BUTTON);
    }

    return false;
  }
}
