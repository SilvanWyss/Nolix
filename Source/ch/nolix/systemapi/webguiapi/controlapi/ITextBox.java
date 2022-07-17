//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITextBox extends IControl<ITextBox, ITextBoxLook> {
	
	//method declaration
	void emptyText();
	
	//method declaration
	String getText();
	
	//method declaration
	TextMode getTextMode();
	
	//method declaration
	ITextBox setText(String text);
	
	//method declaration
	ITextBox setTextMode(TextMode textMode);
}
