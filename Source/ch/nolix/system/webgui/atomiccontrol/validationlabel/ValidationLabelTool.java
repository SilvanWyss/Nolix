package ch.nolix.system.webgui.atomiccontrol.validationlabel;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabelTool;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public final class ValidationLabelTool implements IValidationLabelTool {

  @Override
  public void clearNearestValidationLabelOfControl(final IControl<?, ?> control) {

    final var validationLabel = getOptionalStoredNearestValidationLabelOfControl(control);

    validationLabel.ifPresent(IValidationLabel::clear);
  }

  @Override
  public void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    final IControl<?, ?> control,
    final Runnable action) {
    try {
      action.run();
      clearNearestValidationLabelOfControl(control);
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
      showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
    }
  }

  @Override
  public <C extends IControl<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    C control,
    Consumer<? super C> action) {
    try {
      action.accept(control);
      clearNearestValidationLabelOfControl(control);
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
      showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
    }
  }

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

  @Override
  public void showErrorInNearestValidationLabelOfControlOrSwallowError(
    final IControl<?, ?> control,
    final Throwable error) {

    final var validationLabel = getOptionalStoredNearestValidationLabelOfControl(control);

    validationLabel.ifPresent(l -> l.showError(error));
  }
}
