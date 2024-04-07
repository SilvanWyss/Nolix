//package declaration
package ch.nolix.system.databaseapplication.fieldbinder;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//class
public final class GlobalFieldBinder {

  //constant
  private static final FieldBinderFactory PROPERTY_BINDER_FACTORY = new FieldBinderFactory();

  //constructor
  private GlobalFieldBinder() {
  }

  //static method
  public static <F extends IField> FieldBinding createControlAndBindItWith(final F property) {
    return PROPERTY_BINDER_FACTORY.getFieldBinderForField(property).createControlAndBindItWith(property);
  }
}
