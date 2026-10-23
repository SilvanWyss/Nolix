/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.validationlabel;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IValidationLabelTool {
  /**
   * Clears the nearest {@link IValidationLabel} of the given control if there is
   * such a {@link IValidationLabel}.
   * 
   * @param control
   */
  void clearNearestValidationLabelOfControl(Control<?, ?> control);

  /**
   * Executes the given action of the given control and shows a probable error in
   * the nearest {@link IValidationLabel} of the given control.
   * 
   * @param control
   * @param action
   */
  void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(Control<?, ?> control, Runnable action);

  /**
   * Executes the given action of the given control and shows a probable error in
   * the nearest {@link IValidationLabel} of the given control.
   * 
   * @param control
   * @param action
   * @param <C>     the type of the given control
   */
  <C extends Control<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    C control,
    Consumer<? super C> action);

  /**
   * @param control
   * @return a {@link Optional} with the nearest {@link IValidationLabel} of the
   *         given control if there is such a {@link IValidationLabel}, an empty
   *         {@link Optional} otherwise
   */
  Optional<IValidationLabel> getOptionalStoredNearestValidationLabelOfControl(Control<?, ?> control);

  /**
   * Shows the given error in the nearest {@link IValidationLabel} of the given
   * control if there is such a {@link IValidationLabel}, swallows the given error
   * otherwise.
   * 
   * @param control
   * @param error
   */
  void showErrorInNearestValidationLabelOfControlOrSwallowError(Control<?, ?> control, Throwable error);
}
