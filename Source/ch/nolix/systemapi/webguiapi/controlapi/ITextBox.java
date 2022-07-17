//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITextbox extends IControl<ITextbox, ITextboxLook> {
	
	//method declaration
	void emptyText();
	
	//method declaration
	String getText();
	
	//method declaration
	TextMode getTextMode();
	
	//method declaration
	ITextbox setText(String text);
	
	//method declaration
	ITextbox setTextMode(TextMode textMode);
}
