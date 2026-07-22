/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.templatetest.webgui.dialog;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.control.button.ButtonRole;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.LayerRole;
import ch.nolix.template.webgui.dialog.ShowValueDialogBuilder;

/**
 * @author Silvan Wyss
 */
final class ShowValueDialogBuilderTest extends StandardTest {
  @Test
  void testCase_build() {
    // setup
    final var testUnit = new ShowValueDialogBuilder();

   // execute
    final var result = testUnit.build();

   // verify
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
  }

  @Test
  void testCase_build_thenAddToWebGui_thenClickConfirmButton() {
    // setup
    final var webGui = new WebGui();
    final var testUnit = new ShowValueDialogBuilder();

   // execute part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

   // execute part 2
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

   // verify
    expect(result.belongsToGui()).isFalse();
  }

  private boolean isConfirmButton(final Control<?, ?> control) {
    return //
    control instanceof final IButton button
    && button.hasRole()
    && button.getRole() == ButtonRole.CONFIRM_BUTTON;
  }
}
