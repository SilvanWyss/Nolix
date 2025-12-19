package ch.nolix.systemapi.webatomiccontrol.validationlabel;

import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {
  Throwable getError();

  void showError(Throwable error);
}
