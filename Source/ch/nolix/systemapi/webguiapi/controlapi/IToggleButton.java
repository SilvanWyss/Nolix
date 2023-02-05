//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.requestuniversalapi.OnOffRequestable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IToggleButton extends IControl<IToggleButton, IToggleButtonStyle>, OnOffRequestable {
	
	//method declaration
	void removeOffAction();
	
	//method declaration
	void removeOnAction();
	
	//method declaration
	IToggleButton setOff();
	
	//method declaration
	IToggleButton setOffAction(IAction offAction);
	
	//method declaration
	IToggleButton setOn();
	
	//method declaration
	IToggleButton setOnAction(IAction onAction);
}
