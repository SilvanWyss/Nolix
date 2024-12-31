package ch.nolix.templatetest.webguitest.dialogtest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;
import ch.nolix.template.webgui.dialog.YesNoDialogBuilder;

final class YesNoDialogBuilderTest extends StandardTest {

  @Test
  void testCase_build() {

    //setup
    final var testUnit = new YesNoDialogBuilder();

    //execution
    final var result = testUnit.build();

    //verification 
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
    final var controls = result.getStoredControls();
    expect(controls).contains(this::isConfirmButton);
    expect(controls).contains(this::isCancelButton);
  }

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
    expect(result.belongsToGui()).isFalse();
    Mockito.verify(confirmActionMock, Mockito.never()).run();
  }

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
    expect(result.belongsToGui()).isFalse();
  }

  private boolean isCancelButton(final IControl<?, ?> control) {

    if (control instanceof final IButton button) {
      return (button.getRole() == ButtonRole.CANCEL_BUTTON);
    }

    return false;
  }

  private boolean isConfirmButton(final IControl<?, ?> control) {

    if (control instanceof final IButton button) {
      return (button.getRole() == ButtonRole.CONFIRM_BUTTON);
    }

    return false;
  }
}
