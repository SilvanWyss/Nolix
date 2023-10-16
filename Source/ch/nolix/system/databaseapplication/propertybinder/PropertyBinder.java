//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class PropertyBinder<P extends IProperty, W extends IControl<?, ?>> {

  //method
  public final PropertyBinding bindControlWithProperty(final W control, final P property) {

    final var propertyBinding = new PropertyBinding(property, control);

    bindControlToProperty(control, property, propertyBinding);
    updateControlFromProperty(control, property);

    return propertyBinding;
  }

  //method
  public final PropertyBinding createControlAndBindItWith(final P property) {
    return bindControlWithProperty(createControl(), property);
  }

  //method declaration
  protected abstract void addSelectionOptionsToControlForProperty(W control, P property);

  //method declaration
  protected abstract W createControl();

  //method declaration
  protected abstract void setNoteUpdateActionToControl(W control, Runnable noteUpdateAction);

  //method declaration
  protected abstract void updatePropertyFromControl(P property, W control);

  //method declaration
  protected abstract void updateControlFromProperty(W control, P property);

  //method
  private void bindControlToProperty(final W control, final P property, final PropertyBinding propertyBinding) {

    addSelectionOptionsToControlForProperty(control, property);

    property.setUpdateAction(() -> updateControlFromProperty(control, property));

    setNoteUpdateActionToControl(
        control,
        () -> updatePropertyFromControlCatchingErrors(property, control, propertyBinding));
  }

  //method
  private void updatePropertyFromControlCatchingErrors(
      final P property,
      final W control,
      final PropertyBinding propertyBinding) {
    try {
      updatePropertyFromControl(property, control);
      propertyBinding.removeCurrentError();
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.
      propertyBinding.setCurrentError(error);
    }
  }
}
