/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.templatetest.webgui.dialog;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.control.button.ButtonRole;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.LayerRole;
import ch.nolix.template.webgui.dialog.YesNoDialogBuilder;

/**
 * @author Silvan Wyss
 */
final class YesNoDialogBuilderTest extends StandardTest {
  @Test
  void testCase_build() {
    // setup
    final var testUnit = new YesNoDialogBuilder();

   // execute
    final var result = testUnit.build();

   // verify 
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
    final var controls = result.getStoredControls();
    expect(controls).contains(this::isConfirmButton);
    expect(controls).contains(this::isCancelButton);
  }

  @Test
  void testCase_build_thenClickCancelButton() {
    // setup
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

   // execute part 1
    final var result = testUnit.build();

   // execute part 2
    final var cancelButton = (IButton) result.getStoredControls().getStoredFirst(this::isCancelButton);
    cancelButton.pressLeftMouseButton();

   // verify
    Mockito.verify(confirmActionMock, Mockito.never()).run();
  }

  @Test
  void testCase_build_thenClickConfirmButton() {
    // setup
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

   // execute part 1
    final var result = testUnit.build();

   // execute part 2
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

   // verify
    Mockito.verify(confirmActionMock).run();
  }

  @Test
  void testCase_build_thenAddToWebGui_thenClickCancelButton() {
    // setup
    final var webGui = new WebGui();
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

   // execute part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

   // execute part 2
    final var cancelButton = (IButton) result.getStoredControls().getStoredFirst(this::isCancelButton);
    cancelButton.pressLeftMouseButton();

   // verify
    expect(result.belongsToGui()).isFalse();
    Mockito.verify(confirmActionMock, Mockito.never()).run();
  }

  @Test
  void testCase_build_thenAddToWebGui_thenClickConfirmButton() {
    // setup
    final var webGui = new WebGui();
    final var testUnit = new YesNoDialogBuilder();
    final var confirmActionMock = Mockito.mock(Runnable.class);
    testUnit.setConfirmAction(confirmActionMock);

   // execute part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

   // execute part 2
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

   // verify
    Mockito.verify(confirmActionMock).run();
    expect(result.belongsToGui()).isFalse();
  }

  private boolean isCancelButton(final Control<?, ?> control) {
    if (control instanceof final IButton button) {
      return (button.getRole() == ButtonRole.CANCEL_BUTTON);
    }

    return false;
  }

  private boolean isConfirmButton(final Control<?, ?> control) {
    if (control instanceof final IButton button) {
      return (button.getRole() == ButtonRole.CONFIRM_BUTTON);
    }

    return false;
  }
}
