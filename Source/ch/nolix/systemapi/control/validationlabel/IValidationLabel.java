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
  Throwable getError();

  void showError(Throwable error);
}
