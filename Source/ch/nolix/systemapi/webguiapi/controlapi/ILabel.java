//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILabel<L extends ILabel<L, LS>, LS extends ILabelStyle<LS>> extends IControl<L, LS> {
	
	//method declaration
	String getText();
	
	//method declaration
	L setText(String text);
}
