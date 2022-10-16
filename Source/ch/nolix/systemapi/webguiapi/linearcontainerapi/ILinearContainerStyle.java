//package declaration
package ch.nolix.systemapi.webguiapi.linearcontainerapi;

//own imports
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface ILinearContainerStyle<LCL extends ILinearContainerStyle<LCL>> extends IExtendedControlStyle<LCL> {
	
	//method declaration
	int getChildControlMarginWhenHasState(ControlState state);
	
	//method declaration
	void removeCustomChildControlMargins();
	
	//method declaration
	LCL setChildControlMarginForState(ControlState state, int childControlMargin);
}
