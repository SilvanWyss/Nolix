//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.coreapi.methodapi.mutationapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IValidationLabel extends Clearable, IControl<IValidationLabel, IValidationLabelStyle> {

  //method declaration
  Throwable getError();

  //method declaration
  void showError(Throwable error);
}
