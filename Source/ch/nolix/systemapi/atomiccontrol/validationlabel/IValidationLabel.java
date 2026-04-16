/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.atomiccontrol.validationlabel;

import ch.nolix.baseapi.state.statemutation.Clearable;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {
  Throwable getError();

  void showError(Throwable error);
}
