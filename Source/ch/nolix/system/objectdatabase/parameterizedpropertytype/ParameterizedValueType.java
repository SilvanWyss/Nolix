//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParameterizedValueType<

V>
extends BaseParameterizedValueType<V> {

  //constructor
  public ParameterizedValueType(final Class<V> valueType) {
    super(valueType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.VALUE;
  }
}
