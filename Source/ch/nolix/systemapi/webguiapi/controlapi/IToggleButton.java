//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IToggleButton extends IControl<IToggleButton, IToggleButtonStyle> {
	
	//method declaration
	boolean isOff();
	
	//method declaration
	boolean isOn();
	
	//method declaration
	void removeOffAction();
	
	//method declaration
	void removeOnAction();
	
	//method declaration
	IToggleButton setOff();
	
	//method declaration
	IToggleButton setOffAction(IAction offAcction);
	
	//method declaration
	IToggleButton setOn();
	
	//method declaration
	IToggleButton setOnAction(IAction onAcction);
}
