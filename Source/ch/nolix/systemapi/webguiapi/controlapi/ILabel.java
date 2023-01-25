//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILabel<
	L extends ILabel<L, LS>,
	LS extends ILabelStyle<LS>
> extends IControl<L, LS> {
	
	//method declaration
	LabelRole getRole();
	
	//method declaration
	String getText();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void removeRole();
	
	//method declaration
	L setRole(LabelRole role);
	
	//method declaration
	L setText(String text);
}
