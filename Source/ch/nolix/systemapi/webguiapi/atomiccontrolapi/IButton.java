//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableTextHolder;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IButton extends IControl<IButton, IButtonStyle>, IFluentMutableTextHolder<IButton> {
	
	//method declaration
	ButtonRole getRole();
	
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
	IButton setLeftMouseButtonPressAction(IAction leftMouseButtonPressAction);
	
	//method declaration
	IButton setLeftMouseButtonPressAction(IElementTaker<IButton> leftMouseButtonPressAction);
	
	//method declaration
	IButton setLeftMouseButtonRelaseAction(IAction leftMouseButtonReleaseAction);
	
	//method declaration
	IButton setLeftMouseButtonRelaseAction(IElementTaker<IButton> leftMouseButtonReleaseAction);
	
	//method declaration
	IButton setRole(ButtonRole role);
}
