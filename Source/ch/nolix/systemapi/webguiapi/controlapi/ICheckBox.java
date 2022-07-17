//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ICheckbox extends IControl<ICheckbox, ICheckboxLook> {
	
	//method declaration
	ICheckbox check();
	
	//method declaration
	boolean isChecked();
	
	//method declaration
	ICheckbox unCheck();
}
