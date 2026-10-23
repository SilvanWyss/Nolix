/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.validationlabel;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IValidationLabel extends Clearable, Control<IValidationLabel, IValidationLabelStyle> {
  /**
   * @return the error of the current {@link IValidationLabel}
   * @throws RuntimeException if the current {@link IValidationLabel} does not
   *                          show an error
   */
  Throwable getError();

  /**
   * Lets the current {@link IValidationLabel} show the given error.
   * 
   * @param error
   */
  void showError(Throwable error);
}
