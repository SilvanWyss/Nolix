/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.templatetest.webgui.dialog;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.control.button.ButtonRole;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.webgui.main.LayerRole;
import ch.nolix.template.webgui.dialog.ConfirmCookieDialogBuilder;

/**
 * @author Silvan Wyss
 */
final class ConfirmCookieDialogBuilderTest extends StandardTest {
  @Test
  void testCase_build() {
    // setup
    final var testUnit = new ConfirmCookieDialogBuilder();

    // execute
    final var result = testUnit.build();

    // verify
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
    final var controls = result.getStoredControls();
    expect(controls).contains(c -> c instanceof final IButton button && button.getRole() == ButtonRole.CONFIRM_BUTTON);
  }
}
