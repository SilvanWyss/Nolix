//package declaration
package ch.nolix.system.databaseapplication.fieldbinder;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class FieldBinder<F extends IField, W extends IControl<?, ?>> {

  //method
  public final FieldBinding bindControlWithField(final W control, final F field) {

    final var fieldBinding = new FieldBinding(field, control);

    bindControlToField(control, field, fieldBinding);
    updateControlFromField(control, field);

    return fieldBinding;
  }

  //method
  public final FieldBinding createControlAndBindItWithField(final F field) {
    return bindControlWithField(createControl(), field);
  }

  //method declaration
  protected abstract void addSelectionOptionsToControlForField(W control, F field);

  //method declaration
  protected abstract W createControl();

  //method declaration
  protected abstract void setNoteUpdateActionToControl(W control, Runnable noteUpdateAction);

  //method declaration
  protected abstract void updateFieldFromControl(F field, W control);

  //method declaration
  protected abstract void updateControlFromField(W control, F field);

  //method
  private void bindControlToField(final W control, final F field, final FieldBinding fieldBinding) {

    addSelectionOptionsToControlForField(control, field);

    field.setUpdateAction(() -> updateControlFromField(control, field));

    setNoteUpdateActionToControl(
      control,
      () -> updateFieldFromControlCatchingErrors(field, control, fieldBinding));
  }

  //method
  private void updateFieldFromControlCatchingErrors(
    final F field,
    final W control,
    final FieldBinding fieldBinding) {
    try {
      updateFieldFromControl(field, control);
      fieldBinding.removeCurrentError();
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.
      fieldBinding.setCurrentError(error);
    }
  }
}
