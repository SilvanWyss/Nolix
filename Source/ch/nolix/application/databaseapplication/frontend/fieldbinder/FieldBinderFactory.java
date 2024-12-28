package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public final class FieldBinderFactory {

  private static final ValueBinder VALUE_BINDER = new ValueBinder();

  private static final OptionalValueBinder OPTIONAL_VALUE_BINDER = new OptionalValueBinder();

  private static final ReferenceBinder REFERENCE_BINDER = new ReferenceBinder();

  private static final OptionalReferenceBinder OPTIONAL_REFERENCE_BINDER = new OptionalReferenceBinder();

  @SuppressWarnings("unchecked")
  public <F extends IField> FieldBinder<F, ?> getFieldBinderForField(final F field) {
    return switch (field.getType()) {
      case VALUE ->
        (FieldBinder<F, ?>) VALUE_BINDER;
      case OPTIONAL_VALUE ->
        (FieldBinder<F, ?>) OPTIONAL_VALUE_BINDER;
      case REFERENCE ->
        (FieldBinder<F, ?>) REFERENCE_BINDER;
      case OPTIONAL_BACK_REFERENCE ->
        (FieldBinder<F, ?>) OPTIONAL_REFERENCE_BINDER;
      default ->
        throw InvalidArgumentException.forArgument(field);
    };
  }
}
