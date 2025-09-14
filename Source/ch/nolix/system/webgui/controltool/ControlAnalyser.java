package ch.nolix.system.webgui.controltool;

import ch.nolix.systemapi.webgui.controltool.IControlAnalyser;
import ch.nolix.systemapi.webgui.main.IControl;

public final class ControlAnalyser implements IControlAnalyser {
  @Override
  public boolean firstControlContainsSecondControl(
    final IControl<?, ?> firstControl,
    final IControl<?, ?> secondControl) {
    return //
    firstControl != null
    && firstControlContainsSecondControlWhenFirstControlIsNotNull(firstControl, secondControl);
  }

  private boolean firstControlContainsSecondControlWhenFirstControlIsNotNull(
    final IControl<?, ?> firstControl,
    final IControl<?, ?> secondControl) {
    for (final var c : firstControl.getStoredChildControls()) {
      if (c == secondControl || firstControlContainsSecondControl(c, secondControl)) {
        return true;
      }
    }

    return false;
  }
}
