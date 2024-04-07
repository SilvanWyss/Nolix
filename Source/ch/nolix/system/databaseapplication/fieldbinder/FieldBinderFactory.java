//package declaration
package ch.nolix.system.databaseapplication.fieldbinder;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//class
public final class FieldBinderFactory {

  //constant
  private static final ValueBinder VALUE_BINDER = new ValueBinder();

  //constant
  private static final OptionalValueBinder OPTIONAL_VALUE_BINDER = new OptionalValueBinder();

  //constant
  private static final ReferenceBinder REFERENCE_BINDER = new ReferenceBinder();

  //constant
  private static final OptionalReferenceBinder OPTIONAL_REFERENCE_BINDER = new OptionalReferenceBinder();

  //method
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
