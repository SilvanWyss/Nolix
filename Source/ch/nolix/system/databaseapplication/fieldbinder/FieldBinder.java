//package declaration
package ch.nolix.system.databaseapplication.fieldbinder;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class FieldBinder<F extends IField, W extends IControl<?, ?>> {

  //method
  public final FieldBinding bindControlWithProperty(final W control, final F property) {

    final var fieldBinding = new FieldBinding(property, control);

    bindControlToProperty(control, property, fieldBinding);
    updateControlFromProperty(control, property);

    return fieldBinding;
  }

  //method
  public final FieldBinding createControlAndBindItWith(final F property) {
    return bindControlWithProperty(createControl(), property);
  }

  //method declaration
  protected abstract void addSelectionOptionsToControlForProperty(W control, F property);

  //method declaration
  protected abstract W createControl();

  //method declaration
  protected abstract void setNoteUpdateActionToControl(W control, Runnable noteUpdateAction);

  //method declaration
  protected abstract void updatePropertyFromControl(F property, W control);

  //method declaration
  protected abstract void updateControlFromProperty(W control, F property);

  //method
  private void bindControlToProperty(final W control, final F property, final FieldBinding fieldBinding) {

    addSelectionOptionsToControlForProperty(control, property);

    property.setUpdateAction(() -> updateControlFromProperty(control, property));

    setNoteUpdateActionToControl(
      control,
      () -> updatePropertyFromControlCatchingErrors(property, control, fieldBinding));
  }

  //method
  private void updatePropertyFromControlCatchingErrors(
    final F property,
    final W control,
    final FieldBinding fieldBinding) {
    try {
      updatePropertyFromControl(property, control);
      fieldBinding.removeCurrentError();
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.
      fieldBinding.setCurrentError(error);
    }
  }
}
