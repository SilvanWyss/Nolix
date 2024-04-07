//package declaration
package ch.nolix.system.databaseapplication.fieldbinder;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//class
public final class GlobalFieldBinder {

  //constant
  private static final FieldBinderFactory FIELD_BINDER_FACTORY = new FieldBinderFactory();

  //constructor
  private GlobalFieldBinder() {
  }

  //static method
  public static <F extends IField> FieldBinding createControlAndBindItWith(final F field) {
    return FIELD_BINDER_FACTORY.getFieldBinderForField(field).createControlAndBindItWithField(field);
  }
}
