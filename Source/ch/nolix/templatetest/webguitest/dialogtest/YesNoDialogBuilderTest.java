//package declaration
package ch.nolix.templatetest.webguitest.dialogtest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
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

    //verification
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
    final var controls = result.getStoredControls();
    expect(controls.containsAny(this::isNoButton));
    expect(controls.containsAny(this::isYesButton));
  }

  //method
  private boolean isNoButton(final IControl<?, ?> control) {

    if (control instanceof IButton button) {
      return (button.getRole() == ButtonRole.CANCEL_BUTTON);
    }

    return false;
  }

  //method
  private boolean isYesButton(final IControl<?, ?> control) {

    if (control instanceof IButton button) {
      return (button.getRole() == ButtonRole.CONFIRM_BUTTON);
    }

    return false;
  }
}
