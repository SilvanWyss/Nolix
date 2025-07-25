package ch.nolix.systemapi.webgui.linearcontainer;

import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public interface ILinearContainerStyle<S extends ILinearContainerStyle<S>> extends IControlStyle<S> {

  int getChildControlMarginWhenHasState(ControlState state);

  void removeCustomChildControlMargins();

  S setChildControlMarginForState(ControlState state, int childControlMargin);
}
