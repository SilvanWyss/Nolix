package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface ILinearContainerStyle<LCL extends ILinearContainerStyle<LCL>> extends IControlStyle<LCL> {

  int getChildControlMarginWhenHasState(ControlState state);

  void removeCustomChildControlMargins();

  LCL setChildControlMarginForState(ControlState state, int childControlMargin);
}
