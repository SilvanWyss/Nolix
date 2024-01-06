//package declaration
package ch.nolix.templatetest.webguitest.dialogtest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;
import ch.nolix.template.webgui.dialog.YesNoDialogBuilder;

//class
public final class YesNoDialogBuilderTest extends Test {

  //method
  @TestCase
  public void testCase_build() {

    //setup
    final var testUnit = new YesNoDialogBuilder();

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
                new Button(),
                new Button())));
    expect(result.getStructureSpecification())
      .hasSameStringRepresentationAs(expectedStructure.getStructureSpecification());

    //verification part 2
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
    final var controls = result.getStoredControls();
    expect(controls.containsAny(this::isConfirmButton));
    expect(controls.containsAny(this::isCancelButton));
  }

  //method
  private boolean isCancelButton(final IControl<?, ?> control) {

    if (control instanceof IButton button) {
      return (button.getRole() == ButtonRole.CANCEL_BUTTON);
    }

    return false;
  }

  //method
  private boolean isConfirmButton(final IControl<?, ?> control) {

    if (control instanceof IButton button) {
      return (button.getRole() == ButtonRole.CONFIRM_BUTTON);
    }

    return false;
  }
}
