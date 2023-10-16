//package declaration
package ch.nolix.systemapi.webguiapi.linearcontainerapi;

//own imports
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface ILinearContainerStyle<LCL extends ILinearContainerStyle<LCL>> extends IControlStyle<LCL> {

  //method declaration
  int getChildControlMarginWhenHasState(ControlState state);

  //method declaration
  void removeCustomChildControlMargins();

  //method declaration
  LCL setChildControlMarginForState(ControlState state, int childControlMargin);
}
