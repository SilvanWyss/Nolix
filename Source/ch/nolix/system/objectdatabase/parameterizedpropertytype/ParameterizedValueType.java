//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

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
