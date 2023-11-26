//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//Java imports
import java.util.function.Consumer;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IValidationLabelHelper {

  //method declaration
  void clearNearestValidationLabelOfControl(IControl<?, ?> control);

  //method declaration
  void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(IControl<?, ?> control, Runnable action);

  //method declaration
  <C extends IControl<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    C control,
    Consumer<? super C> action);

  //method declaration
  IValidationLabel getStoredNearestValidationLabelOfControlOrNull(IControl<?, ?> control);

  //method declaration
  void showErrorInNearestValidationLabelOfControlOrSwallowError(IControl<?, ?> control, Throwable error);
}
