//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ICheckBox extends IControl<ICheckBox, ICheckBoxLook> {
	
	//method declaration
	ICheckBox check();
	
	//method declaration
	boolean isChecked();
	
	//method declaration
	ICheckBox unCheck();
}
