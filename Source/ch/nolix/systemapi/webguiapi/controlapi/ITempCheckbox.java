//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITempCheckbox extends IControl<ITempCheckbox, ICheckboxStyle> {
	
	//method declaration
	ITempCheckbox check();
	
	//method declaration
	boolean isChecked();
	
	//method declaration
	ITempCheckbox unCheck();
}
