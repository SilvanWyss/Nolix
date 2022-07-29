//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.IRespondingMutableElement;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.textformatapi.Font;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IControlStyle<CS extends IControlStyle<CS>> extends IRespondingMutableElement<CS> {
	
	//method declaration
	void removeCustomBoldTextFlags();
	
	//method declaration
	void removeCustomFonts();
	
	//method declaration
	void removeCustomOpacities();
	
	//method declaration
	void removeCustomTextColors();
	
	//method declaration
	void removeCustomTextSizes();
	
	//method declaration
	CS setBoldTextFlagForState(ControlState state, boolean boldTextFlag);
	
	//method declaration
	CS setFontForState(ControlState state, Font font);
	
	//method declaration
	CS setOpacityForState(ControlState state, double opacity);
	
	//method declaration
	CS setTextColorForState(ControlState state, IColor textColor);
	
	//method declaration
	CS setTextSizeForState(ControlState state, int textSize);
}
