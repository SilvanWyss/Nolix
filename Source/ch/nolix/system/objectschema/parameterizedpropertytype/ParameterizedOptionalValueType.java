//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ParameterizedOptionalValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedOptionalValueType(final DataType dataType) {
    super(dataType);
  }

  //static method
  public static <V2> ParameterizedOptionalValueType<V2> forDataType(final DataType dataType) {
    return new ParameterizedOptionalValueType<>(dataType);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.OPTIONAL_VALUE;
  }
}
