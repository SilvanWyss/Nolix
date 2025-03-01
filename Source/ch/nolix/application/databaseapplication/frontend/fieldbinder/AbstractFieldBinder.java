package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class AbstractFieldBinder<F extends IField, W extends IControl<?, ?>> {

  public final FieldBinding bindControlWithField(final W control, final F field) {

    final var fieldBinding = new FieldBinding(field, control);

    bindControlToField(control, field, fieldBinding);
    updateControlFromField(control, field);

    return fieldBinding;
  }

  public final FieldBinding createControlAndBindItWithField(final F field) {
    return bindControlWithField(createControl(), field);
  }

  protected abstract void addSelectionOptionsToControlForField(W control, F field);

  protected abstract W createControl();

  protected abstract void setNoteUpdateActionToControl(W control, Runnable noteUpdateAction);

  protected abstract void updateFieldFromControl(F field, W control);

  protected abstract void updateControlFromField(W control, F field);

  private void bindControlToField(final W control, final F field, final FieldBinding fieldBinding) {

    addSelectionOptionsToControlForField(control, field);

    field.setUpdateAction(() -> updateControlFromField(control, field));

    setNoteUpdateActionToControl(
      control,
      () -> updateFieldFromControlCatchingErrors(field, control, fieldBinding));
  }

  private void updateFieldFromControlCatchingErrors(
    final F field,
    final W control,
    final FieldBinding fieldBinding) {
    try {
      updateFieldFromControl(field, control);
      fieldBinding.removeCurrentError();
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
      fieldBinding.setCurrentError(error);
    }
  }
}
