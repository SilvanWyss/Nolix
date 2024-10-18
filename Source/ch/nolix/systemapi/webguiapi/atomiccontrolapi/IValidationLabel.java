package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {

  Throwable getError();

  void showError(Throwable error);
}
