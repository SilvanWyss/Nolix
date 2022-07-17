//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IRespondingMutableElement;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.textformatapi.Font;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//interface
public interface IControlLook<CL extends IControlLook<CL>> extends IRespondingMutableElement<CL> {
	
	//method declaration
	boolean getBoldTextFlag();
	
	//method declaration
	Font getFont();
	
	//method declaration
	double getOpacity();
	
	//method declaration
	Color getTextColor();
	
	//method declaration
	int getTextSize();
	
	//method declaration
	void removeBoldTextFlags();
	
	//method declaration
	void removeFonts();
	
	//method declaration
	void removeTextColors();
	
	//method declaration
	void removeTextSizes();
	
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
