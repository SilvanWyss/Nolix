//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabelHelper;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class ValidationLabelHelper implements IValidationLabelHelper {
	
	//method
	@Override
	public void clearNearestValidationLabelOfControl(final IControl<?, ?> control) {
		
		final var validationLabel = getStoredNearestValidationLabelOfControlOrNull(control);
		
		if (validationLabel != null) {
			validationLabel.clear();
		}
	}
	
	//method
	@Override
	public void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
		final IControl<?, ?> control,
		final IAction action
	) {
		try {
			action.run();
			clearNearestValidationLabelOfControl(control);
		} catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.
			showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
		}
	}
	
	//method
	@Override
	public <C extends IControl<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
		C control,
		IElementTaker<? super C> action
	) {
		try {
			action.run(control);
			clearNearestValidationLabelOfControl(control);
		} catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.
			showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
		}
	}
	
	//method
	@Override
	public IValidationLabel getStoredNearestValidationLabelOfControlOrNull(final IControl<?, ?> control) {
		
		if (control.belongsToControl()) {
			
			final var parentControl = control.getStoredParentControl();
			
			for (final var cc : parentControl.getStoredChildControls()) {
				if (cc instanceof final IValidationLabel validationLabel) {
					return validationLabel;
				}
			}
			
			return getStoredNearestValidationLabelOfControlOrNull(parentControl);
		}
		
		return null;
	}
	
	//method
	@Override
	public void showErrorInNearestValidationLabelOfControlOrSwallowError(
		final IControl<?, ?> control,
		final Throwable error
	) {
		
		final var validationLabel = getStoredNearestValidationLabelOfControlOrNull(control);
		
		if (validationLabel	!= null) {
			validationLabel.showError(error);
		}
	}
}
