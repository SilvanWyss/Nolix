//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ParameterizedMultiValueType<

V>
extends BaseParameterizedValueType<V> {

  //constructor
  public ParameterizedMultiValueType(final Class<V> valueType) {
    super(valueType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_VALUE;
  }
}
