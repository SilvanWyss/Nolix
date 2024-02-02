//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ParameterizedMultiValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedMultiValueType(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> ParameterizedMultiValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedMultiValueType<>(valueType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_VALUE;
  }
}
