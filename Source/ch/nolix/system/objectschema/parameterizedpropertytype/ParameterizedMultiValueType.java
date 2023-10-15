//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParameterizedMultiValueType<V> extends BaseParameterizedValueType<V> {

  // constructor
  public ParameterizedMultiValueType(final DataType dataType) {
    super(dataType);
  }

  // method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_VALUE;
  }
}
