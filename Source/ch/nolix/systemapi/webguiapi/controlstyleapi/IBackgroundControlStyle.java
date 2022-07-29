//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//own imports
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IBackgroundControlStyle<BCS extends IBackgroundControlStyle<BCS>> {
	
	//method declaration
	void removeCustomBackgrounds();
	
	//method declaration
	BCS setBackgroundColorForState(ControlState state, IColor backgroundColor);
	
	//method declaration
	BCS setBackgroundColorGradientForState(ControlState state, IColorGradient backgroundColorGradient);
	
	//method declaration
	BCS setBackgroundImageForState(ControlState state, IImage backgroundImage, ImageApplication imageApplication);
	
	//method declaration
	BCS setBackgroundForState(ControlState state, IBackground background);
}
