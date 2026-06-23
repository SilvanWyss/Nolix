/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.atomiccontrol.validationlabel;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IValidationLabelTool {
  void clearNearestValidationLabelOfControl(Control<?, ?> control);

  void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(Control<?, ?> control, Runnable action);

  <C extends Control<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    C control,
    Consumer<? super C> action);

  Optional<IValidationLabel> getOptionalStoredNearestValidationLabelOfControl(Control<?, ?> control);

  void showErrorInNearestValidationLabelOfControlOrSwallowError(Control<?, ?> control, Throwable error);
}
