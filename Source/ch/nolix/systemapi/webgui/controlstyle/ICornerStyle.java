package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public interface ICornerStyle<S extends ICornerStyle<S>> {
  int getCornerRadiusWhenHasState(ControlState state);

  S forStateSetCornerRadius(ControlState state, int cornerRadius);

  void removeCustomCornerRadiuses();
}
