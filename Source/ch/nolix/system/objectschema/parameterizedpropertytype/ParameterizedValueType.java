//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ParameterizedValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedValueType(final DataType dataType) {
    super(dataType);
  }

  //static method
  public static <V2> ParameterizedValueType<V2> forDataType(final DataType dataType) {
    return new ParameterizedValueType<V2>(dataType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.VALUE;
  }
}
