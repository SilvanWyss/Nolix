//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

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
  public FieldType getFieldType() {
    return FieldType.MULTI_VALUE;
  }
}
