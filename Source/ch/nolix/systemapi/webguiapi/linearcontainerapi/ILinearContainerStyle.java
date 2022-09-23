//package declaration
package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//interface
public interface ILinearContainerStyle<LCL extends ILinearContainerStyle<LCL>> extends IExtendedControlStyle<LCL> {
	
	//method declaration
	int getChildControlMarginWhenHasState(ControlState state);
	
	//method declaration
	void removeCustomChildControlMargins();
	
	//method declaration
	LCL setChildControlMarginForState(ControlState state, int childControlMargin);
}
