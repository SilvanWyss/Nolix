//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILabel extends IControl<ILabel, ILabelStyle> {
	
	//method declaration
	String getText();
	
	//method declaration
	ITextbox setText(String text);
}
