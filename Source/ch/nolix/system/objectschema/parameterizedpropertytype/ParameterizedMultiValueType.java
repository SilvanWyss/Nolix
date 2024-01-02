//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ParameterizedMultiValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedMultiValueType(final DataType dataType) {
    super(dataType);
  }

  //static method
  public static <V2> ParameterizedMultiValueType<V2> forDataType(final DataType dataType) {
    return new ParameterizedMultiValueType<>(dataType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_VALUE;
  }
}
