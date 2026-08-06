/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.validationlabel;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.systemapi.control.validationlabel.IValidationLabel;
import ch.nolix.systemapi.control.validationlabel.IValidationLabelTool;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class ValidationLabelTool implements IValidationLabelTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public void clearNearestValidationLabelOfControl(final Control<?, ?> control) {
    final var validationLabel = getOptionalStoredNearestValidationLabelOfControl(control);

    validationLabel.ifPresent(IValidationLabel::clear);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    final Control<?, ?> control,
    final Runnable action) {
    try {
      action.run();
      clearNearestValidationLabelOfControl(control);
    } catch (final Throwable error) { // NOSONAR: All errors must be caught.
      showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Control<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    C control,
    Consumer<? super C> action) {
    try {
      action.accept(control);
      clearNearestValidationLabelOfControl(control);
    } catch (final Throwable error) { // NOSONAR: All errors must be caught.
      showErrorInNearestValidationLabelOfControlOrSwallowError(control, error);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<IValidationLabel> getOptionalStoredNearestValidationLabelOfControl(final Control<?, ?> control) {
    if (control.belongsToControl()) {
      final var parentControl = control.getStoredParentControl();

      for (final var c : parentControl.getStoredChildControls()) {
        if (c instanceof final IValidationLabel validationLabel) {
          return Optional.of(validationLabel);
        }
      }

      return getOptionalStoredNearestValidationLabelOfControl(parentControl);
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void showErrorInNearestValidationLabelOfControlOrSwallowError(
    final Control<?, ?> control,
    final Throwable error) {
    final var validationLabel = getOptionalStoredNearestValidationLabelOfControl(control);

    validationLabel.ifPresent(l -> l.showError(error));
  }
}
