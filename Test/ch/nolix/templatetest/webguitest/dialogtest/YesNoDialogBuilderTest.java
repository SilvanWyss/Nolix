//package declaration
package ch.nolix.templatetest.webguitest.dialogtest;

import org.junit.jupiter.api.Test;
//Mockito imports
import org.mockito.Mockito;

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
import ch.nolix.template.webgui.dialog.YesNoDialogBuilder;

//class
final class YesNoDialogBuilderTest extends StandardTest {

  //method
  @Test
  void testCase_build() {

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
  @Test
  void testCase_build_thenClickCancelButton() {

    //setup
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

    //execution part 1
    final var result = testUnit.build();

    //execution part 2
    final var cancelButton = (IButton) result.getStoredControls().getStoredFirst(this::isCancelButton);
    cancelButton.pressLeftMouseButton();

    //verification
    Mockito.verify(confirmActionMock, Mockito.never()).run();
  }

  //method
  @Test
  void testCase_build_thenClickConfirmButton() {

    //setup
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

    //execution part 1
    final var result = testUnit.build();

    //execution part 2
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

    //verification
    Mockito.verify(confirmActionMock).run();
  }

  //method
  @Test
  void testCase_build_thenAddToWebGui_thenClickCancelButton() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

    //execution part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

    //execution part 2
    final var cancelButton = (IButton) result.getStoredControls().getStoredFirst(this::isCancelButton);
    cancelButton.pressLeftMouseButton();

    //verification
    expectNot(result.belongsToGui());
    Mockito.verify(confirmActionMock, Mockito.never()).run();
  }

  //method
  @Test
  void testCase_build_thenAddToWebGui_thenClickConfirmButton() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

    //execution part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

    //execution part 2
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

    //verification
    Mockito.verify(confirmActionMock).run();
    expectNot(result.belongsToGui());
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
