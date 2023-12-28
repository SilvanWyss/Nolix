//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ParameterizedOptionalValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedOptionalValueType(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> ParameterizedOptionalValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedOptionalValueType<>(valueType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.OPTIONAL_VALUE;
  }
}
