//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

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
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_VALUE;
  }
}