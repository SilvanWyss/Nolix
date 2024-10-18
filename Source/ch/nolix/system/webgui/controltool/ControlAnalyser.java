package ch.nolix.system.webgui.controltool;

import ch.nolix.systemapi.webguiapi.controltoolapi.IControlAnalyser;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

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

    for (final var cc : firstControl.getStoredChildControls()) {
      if (cc == secondControl || firstControlContainsSecondControl(cc, secondControl)) {
        return true;
      }
    }

    return false;
  }
}
