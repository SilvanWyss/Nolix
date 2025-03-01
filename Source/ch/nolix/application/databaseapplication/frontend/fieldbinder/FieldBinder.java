package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public final class FieldBinder {

  private static final FieldBinderFactory FIELD_BINDER_FACTORY = new FieldBinderFactory();

  private FieldBinder() {
  }

  public static <F extends IField> FieldBinding createControlAndBindItWith(final F field) {
    return FIELD_BINDER_FACTORY.getFieldBinderForField(field).createControlAndBindItWithField(field);
  }
}
