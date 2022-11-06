//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.IRespondingMutableElement;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.fontapi.Font;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface IControlStyle<CS extends IControlStyle<CS>> extends IRespondingMutableElement<CS> {
	
	//method declaration
	<CS2 extends IControlStyle<CS2>> void addChild(final CS2 controlStyle);
	
	//method declaration
	boolean getBoldTextFlagWhenHasState(ControlState state);
	
	//method declaration
	Font getFontWhenHasState(ControlState state);
	
	//method declaration
	double getOpacityWhenHasState(ControlState state);
	
	//method declaration
	IColor getTextColorWhenHasState(ControlState state);	
	
	//method declaration
	int getTextSizeWhenHasState(ControlState state);
	
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
