package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.webgui.main.ControlState;

public interface ICornerStyle<S extends ICornerStyle<S>> {
  int getCornerRadiusWhenHasState(ControlState state);

  void removeCustomCornerRadiuses();

  S setCornerRadiusForState(ControlState state, int cornerRadius);
}
