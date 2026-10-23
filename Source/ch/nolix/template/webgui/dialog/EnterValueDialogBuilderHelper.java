/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.dialog;

import java.util.function.Consumer;

import ch.nolix.systemapi.control.textbox.ITextbox;

/**
 * Of the {@link EnterValueDialogBuilderHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class EnterValueDialogBuilderHelper {
  private EnterValueDialogBuilderHelper() {
  }

  /**
   * Passes the text of the given valueTextbox to the given valueTaker.
   * 
   * @param valueTextbox
   * @param valueTaker
   */
  public static void confirmNewValue(final ITextbox valueTextbox, final Consumer<String> valueTaker) {
    final var newValue = valueTextbox.getText();

    valueTaker.accept(newValue);
    valueTextbox.getStoredParentLayer().removeSelfFromGui();
  }
}
