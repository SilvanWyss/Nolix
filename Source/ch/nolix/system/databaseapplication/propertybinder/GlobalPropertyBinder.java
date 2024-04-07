//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//class
public final class GlobalPropertyBinder {

  //constant
  private static final PropertyBinderFactory PROPERTY_BINDER_FACTORY = new PropertyBinderFactory();

  //constructor
  private GlobalPropertyBinder() {
  }

  //static method
  public static <P extends IField> PropertyBinding createControlAndBindItWith(final P property) {
    return PROPERTY_BINDER_FACTORY.getPropertyBinderFor(property).createControlAndBindItWith(property);
  }
}
