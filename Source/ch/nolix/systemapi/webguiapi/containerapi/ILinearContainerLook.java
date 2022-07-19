//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.guiapi.controllookapi.IBorderControlLook;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface ILinearContainerLook<LCL extends ILinearContainerLook<LCL>> extends IBorderControlLook<LCL> {
	
	//method declaration
	int getControlMargin();
	
	//method declaration
	LCL setControlMarginForState(ControlState state, int controlMargin);
}
