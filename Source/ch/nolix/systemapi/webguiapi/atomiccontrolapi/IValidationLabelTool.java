package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IValidationLabelTool {

  void clearNearestValidationLabelOfControl(IControl<?, ?> control);

  void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(IControl<?, ?> control, Runnable action);

  <C extends IControl<C, ?>> void executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
    C control,
    Consumer<? super C> action);

  Optional<IValidationLabel> getOptionalStoredNearestValidationLabelOfControl(IControl<?, ?> control);

  void showErrorInNearestValidationLabelOfControlOrSwallowError(IControl<?, ?> control, Throwable error);
}
