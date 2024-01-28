//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//Java imports
import java.util.Optional;
import java.util.function.Consumer;

//own imports
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabelTool;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class ValidationLabelTool implements IValidationLabelTool {

  //method
  @Override
  public void clearNearestValidationLabelOfControl(final IControl<?, ?> control) {

    final var validationLabel = getOptionalStoredNearestValidationLabelOfControl(control);

    validationLabel.ifPresent(IValidationLabel::clear);
  }

  //method
  @Override
  public void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    final IControl<?, ?> control,
    final Runnable action) {
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
    Consumer<? super C> action) {
    try {
      action.accept(control);
      clearNearestValidationLabelOfControl(control);
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.
      showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
    }
  }

  //method
  @Override
  public Optional<IValidationLabel> getOptionalStoredNearestValidationLabelOfControl(final IControl<?, ?> control) {

    if (control.belongsToControl()) {

      final var parentControl = control.getStoredParentControl();

      for (final var cc : parentControl.getStoredChildControls()) {
        if (cc instanceof final IValidationLabel validationLabel) {
          return Optional.of(validationLabel);
        }
      }

      return getOptionalStoredNearestValidationLabelOfControl(parentControl);
    }

    return Optional.empty();
  }

  //method
  @Override
  public void showErrorInNearestValidationLabelOfControlOrSwallowError(
    final IControl<?, ?> control,
    final Throwable error) {

    final var validationLabel = getOptionalStoredNearestValidationLabelOfControl(control);

    validationLabel.ifPresent(l -> l.showError(error));
  }
}
