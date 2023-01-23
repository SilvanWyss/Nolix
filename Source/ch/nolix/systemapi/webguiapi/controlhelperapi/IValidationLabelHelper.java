//package declaration
package ch.nolix.systemapi.webguiapi.controlhelperapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.systemapi.webguiapi.controlapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IValidationLabelHelper {
	
	//method declaration
	void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(IControl<?, ?> control, IAction action);
	
	//method declaration
	IValidationLabel<?, ?> getRefNearestValidationLabelOfControlOrNull(IControl<?, ?> control);
	
	//method declaration
	void showErrorInNearestValidationLabelOfControlOrSwallowError(IControl<?, ?> control, Throwable error);
}
