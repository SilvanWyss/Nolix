//package declaration
package ch.nolix.system.webgui.controlhelper;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.controlapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.controlhelperapi.IValidationLabelHelper;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class ValidationLabelHelper implements IValidationLabelHelper {
	
	//method
	@Override
	public void clearNearestValidationLabelOfControl(final IControl<?, ?> control) {
		
		final var validationLabel = getOriNearestValidationLabelOfControlOrNull(control);
		
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
		} catch (final Throwable error) {
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
		} catch (final Throwable error) {
			showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
		}
	}
	
	//method
	@Override
	public IValidationLabel getOriNearestValidationLabelOfControlOrNull(final IControl<?, ?> control) {
		
		if (control.belongsToControl()) {
			
			final var parentControl = control.getOriParentControl();
			
			for (final var cc : parentControl.getOriChildControls()) {
				if (cc instanceof final IValidationLabel validationLabel) {
					return validationLabel;
				}
			}
			
			return getOriNearestValidationLabelOfControlOrNull(parentControl);
		}
		
		return null;
	}
	
	//method
	@Override
	public void showErrorInNearestValidationLabelOfControlOrSwallowError(
		final IControl<?, ?> control,
		final Throwable error
	) {
		
		final var validationLabel = getOriNearestValidationLabelOfControlOrNull(control);
		
		if (validationLabel	!= null) {
			validationLabel.showError(error);
		}
	}
}
