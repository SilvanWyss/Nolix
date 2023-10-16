//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.coreapi.functionapi.mutationapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {

  //method declaration
  Throwable getError();

  //method declaration
  void showError(Throwable error);
}
