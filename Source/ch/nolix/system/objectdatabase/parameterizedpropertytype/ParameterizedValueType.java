//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ParameterizedValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedValueType(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> ParameterizedValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedValueType<>(valueType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.VALUE;
  }
}
