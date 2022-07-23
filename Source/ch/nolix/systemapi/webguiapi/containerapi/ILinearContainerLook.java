//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controllookapi.IExtendedControlLook;

//interface
public interface ILinearContainerLook<LCL extends ILinearContainerLook<LCL>> extends IExtendedControlLook<LCL> {
	
	//method declaration
	int getControlMargin();
	
	//method declaration
	void removeCustomControlMargins();
	
	//method declaration
	LCL setControlMarginForState(ControlState state, int controlMargin);
}
