//package declaration
package ch.nolix.systemapi.webguiapi.controlhelperapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IValidationLabelHelper {
	
	//method declaration
	void clearNearestValidationLabelOfControl(IControl<?, ?> control);
	
	//method declaration
	void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(IControl<?, ?> control, IAction action);
	
	//method declaration
	<C extends IControl<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
		C control,
		IElementTaker<? super C> action
	);
	
	//method declaration
	IValidationLabel getOriNearestValidationLabelOfControlOrNull(IControl<?, ?> control);
	
	//method declaration
	void showErrorInNearestValidationLabelOfControlOrSwallowError(IControl<?, ?> control, Throwable error);
}
