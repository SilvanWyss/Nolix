//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//class
public final class ParameterizedValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedValueType(final DataType dataType) {
    super(dataType);
  }

  //static method
  public static <V2> ParameterizedValueType<V2> forDataType(final DataType dataType) {
    return new ParameterizedValueType<>(dataType);
  }

  //method
  @Override
  public FieldType getPropertyType() {
    return FieldType.VALUE;
  }
}
