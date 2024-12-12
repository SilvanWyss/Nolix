package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface ILinearContainerStyle<S extends ILinearContainerStyle<S>> extends IControlStyle<S> {

  int getChildControlMarginWhenHasState(ControlState state);

  void removeCustomChildControlMargins();

  S setChildControlMarginForState(ControlState state, int childControlMargin);
}
