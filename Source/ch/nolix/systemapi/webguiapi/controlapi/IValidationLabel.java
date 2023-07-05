//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {
	
	//method declaration
	Throwable getError();
	
	//method declaration
	void showError(Throwable error);
}
