//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//interface
public interface ILinearContainerStyle<LCL extends ILinearContainerStyle<LCL>> extends IExtendedControlStyle<LCL> {
	
	//method declaration
	int getControlMargin();
	
	//method declaration
	void removeCustomControlMargins();
	
	//method declaration
	LCL setControlMarginForState(ControlState state, int controlMargin);
}
