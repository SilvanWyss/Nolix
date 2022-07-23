//package declaration
package ch.nolix.systemapi.webguiapi.controllookapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.IRespondingMutableElement;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.textformatapi.Font;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IControlLook<CL extends IControlLook<CL>> extends IRespondingMutableElement<CL> {
	
	//method declaration
	void removeCustomBoldTextFlags();
	
	//method declaration
	void removeCustomFonts();
	
	//method declaration
	void removeCustomTextColors();
	
	//method declaration
	void removecCustomTextSizes();
	
	//method declaration
	CL setBoldTextFlagForState(ControlState state, boolean boldTextFlag);
	
	//method declaration
	CL setFontFotState(ControlState state, Font font);
	
	//method declaration
	CL setOpacityFotState(ControlState state, double opacity);
	
	//method declaration
	CL setTextColororState(ControlState state, IColor textColor);
	
	//method declaration
	CL setTextSizeForState(ControlState state, int textSize);
}
