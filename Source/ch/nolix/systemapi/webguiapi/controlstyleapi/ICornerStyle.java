package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface ICornerStyle<S extends ICornerStyle<S>> {

  int getCornerRadiusWhenHasState(ControlState state);

  void removeCustomCornerRadiuses();

  S setCornerRadiusForState(ControlState state, int cornerRadius);
}
