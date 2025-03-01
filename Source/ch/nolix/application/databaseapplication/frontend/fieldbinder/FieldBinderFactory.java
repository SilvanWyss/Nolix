package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public final class FieldBinderFactory {

  private static final ValueBinder VALUE_BINDER = new ValueBinder();

  private static final OptionalValueBinder OPTIONAL_VALUE_BINDER = new OptionalValueBinder();

  private static final ReferenceBinder REFERENCE_BINDER = new ReferenceBinder();

  private static final OptionalReferenceBinder OPTIONAL_REFERENCE_BINDER = new OptionalReferenceBinder();

  @SuppressWarnings("unchecked")
  public <F extends IField> AbstractFieldBinder<F, ?> getFieldBinderForField(final F field) {
    return switch (field.getType()) {
      case VALUE ->
        (AbstractFieldBinder<F, ?>) VALUE_BINDER;
      case OPTIONAL_VALUE ->
        (AbstractFieldBinder<F, ?>) OPTIONAL_VALUE_BINDER;
      case REFERENCE ->
        (AbstractFieldBinder<F, ?>) REFERENCE_BINDER;
      case OPTIONAL_BACK_REFERENCE ->
        (AbstractFieldBinder<F, ?>) OPTIONAL_REFERENCE_BINDER;
      default ->
        throw InvalidArgumentException.forArgument(field);
    };
  }
}
