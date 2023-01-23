//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IButton<
	B extends IButton<B, BS>,
	BS extends IButtonStyle<BS>
>
extends IControl<B, BS> {
	
	//method declaration
	ButtonRole getRole();
	
	//method declaration
	String getText();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void pressLeftMouseButton();
	
	//method declaration
	void releaseLeftMouseButton();
	
	//method declaration
	void removeLeftMouseButtonPressAction();
	
	//method declaration
	void removeLeftMouseButtonReleaseAction();
	
	//method declaration
	void removeRole();
	
	//method declaration
	B setLeftMouseButtonPressAction(IAction leftMouseButtonPressAction);
	
	//method declaration
	B setLeftMouseButtonPressAction(IElementTaker<IButton<?, ?>> leftMouseButtonPressAction);
	
	//method declaration
	B setLeftMouseButtonRelaseAction(IAction leftMouseButtonReleaseAction);
	
	//method declaration
	B setLeftMouseButtonRelaseAction(IElementTaker<IButton<?, ?>> leftMouseButtonReleaseAction);
	
	//method declaration
	B setRole(ButtonRole role);
	
	//method declaration
	B setText(String text);
}
