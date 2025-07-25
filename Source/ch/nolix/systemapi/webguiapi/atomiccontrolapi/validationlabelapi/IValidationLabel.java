package ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi;

import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {

  Throwable getError();

  void showError(Throwable error);
}
