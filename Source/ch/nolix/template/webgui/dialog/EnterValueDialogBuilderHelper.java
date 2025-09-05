package ch.nolix.template.webgui.dialog;

import java.util.function.Consumer;

import ch.nolix.systemapi.webgui.atomiccontrol.textboxapi.ITextbox;

public final class EnterValueDialogBuilderHelper {
  private EnterValueDialogBuilderHelper() {
  }

  public static void confirmNewValue(final ITextbox valueTextbox, final Consumer<String> valueTaker) {
    final var newValue = valueTextbox.getText();

    valueTaker.accept(newValue);
    valueTextbox.getStoredParentLayer().removeSelfFromGui();
  }
}
