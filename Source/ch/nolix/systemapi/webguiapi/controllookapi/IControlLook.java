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
	void removeCustomOpacities();
	
	//method declaration
	void removeCustomTextColors();
	
	//method declaration
	void removeCustomTextSizes();
	
	//method declaration
	CL setBoldTextFlagForState(ControlState state, boolean boldTextFlag);
	
	//method declaration
	CL setFontForState(ControlState state, Font font);
	
	//method declaration
	CL setOpacityForState(ControlState state, double opacity);
	
	//method declaration
	CL setTextColorForState(ControlState state, IColor textColor);
	
	//method declaration
	CL setTextSizeForState(ControlState state, int textSize);
}
