//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

//class
public final class PropertyBinderFactory {

  // constant
  private static final ValueBinder VALUE_BINDER = new ValueBinder();

  // constant
  private static final OptionalValueBinder OPTIONAL_VALUE_BINDER = new OptionalValueBinder();

  // constant
  private static final ReferenceBinder REFERENCE_BINDER = new ReferenceBinder();

  // constant
  private static final OptionalReferenceBinder OPTIONAL_REFERENCE_BINDER = new OptionalReferenceBinder();

  // method
  @SuppressWarnings("unchecked")
  public <P extends IProperty> PropertyBinder<P, ?> getPropertyBinderFor(final P property) {
    return switch (property.getType()) {
      case VALUE ->
        (PropertyBinder<P, ?>) VALUE_BINDER;
      case OPTIONAL_VALUE ->
        (PropertyBinder<P, ?>) OPTIONAL_VALUE_BINDER;
      case REFERENCE ->
        (PropertyBinder<P, ?>) REFERENCE_BINDER;
      case OPTIONAL_BACK_REFERENCE ->
        (PropertyBinder<P, ?>) OPTIONAL_REFERENCE_BINDER;
      default ->
        throw InvalidArgumentException.forArgument(property);
    };
  }
}
