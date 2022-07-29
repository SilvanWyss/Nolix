//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.systemapi.guiapi.controlrole.ButtonRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IButton extends IControl<IButton, IButtonStyle> {
	
	//method declaration
	ButtonRole getRole();
	
	//method declaration
	String getText();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void removeRole();
	
	//method declaration
	IButton setLeftMouseButtonPressAction(IAction leftMouseButtonPressAction);
	
	//method declaration
	IButton setLeftMouseButtonRelaseAction(IAction leftMouseButtonReleaseAction);
	
	//method declaration
	IButton setRole(ButtonRole buttonRole);
	
	//method declaration
	IButton setText(String text);
}
