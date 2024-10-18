package ch.nolix.system.databaseapplication.fieldbinder;

import ch.nolix.systemapi.objectdataapi.dataapi.IField;

public final class GlobalFieldBinder {

  private static final FieldBinderFactory FIELD_BINDER_FACTORY = new FieldBinderFactory();

  private GlobalFieldBinder() {
  }

  public static <F extends IField> FieldBinding createControlAndBindItWith(final F field) {
    return FIELD_BINDER_FACTORY.getFieldBinderForField(field).createControlAndBindItWithField(field);
  }
}
