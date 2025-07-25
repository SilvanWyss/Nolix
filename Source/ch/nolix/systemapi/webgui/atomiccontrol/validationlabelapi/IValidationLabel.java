package ch.nolix.systemapi.webgui.atomiccontrol.validationlabelapi;

import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.systemapi.webgui.main.IControl;

public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {

  Throwable getError();

  void showError(Throwable error);
}
