//package declaration
package ch.nolix.systemapi.guiapi.controllookapi;

//own imports
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IBackgroundControlLook<BCL extends IBackgroundControlLook<BCL>> {
	
	//method declaration
	IBackground getBackground();
	
	//method declaration
	void removeCustomBackgrounds();
	
	//method declaration
	BCL setBackgroundForState(ControlState state, IBackground background);
}
