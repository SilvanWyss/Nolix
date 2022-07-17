//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILabel extends IControl<ILabel, ILabelLook> {
	
	//method declaration
	String getText();
	
	//method declaration
	ITextBox setText(String text);
}
