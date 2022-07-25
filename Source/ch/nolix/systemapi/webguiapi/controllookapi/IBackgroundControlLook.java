//package declaration
package ch.nolix.systemapi.webguiapi.controllookapi;

//own imports
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IBackgroundControlLook<BCL extends IBackgroundControlLook<BCL>> {
	
	//method declaration
	void removeCustomBackgrounds();
	
	//method declaration
	BCL setBackgroundColorForState(ControlState state, IColor backgroundColor);
	
	//method declaration
	BCL setBackgroundColorGradientForState(ControlState state, IColorGradient backgroundColorGradient);
	
	//method declaration
	BCL setBackgroundImageForState(ControlState state, IImage backgroundImage, ImageApplication imageApplication);
	
	//method declaration
	BCL setBackgroundForState(ControlState state, IBackground background);
}
