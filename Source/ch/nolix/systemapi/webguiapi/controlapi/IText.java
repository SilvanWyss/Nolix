//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IText<
	T extends IText<T, TS>,
	TS extends ITextStyle<TS>
> extends IControl<T, TS> {
	
	//method declaration
	TextRole getRole();
	
	//method declaration
	String getText();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void removeRole();
	
	//method declaration
	T setRole(TextRole role);
	
	//method declaration
	T setText(String text);
}
