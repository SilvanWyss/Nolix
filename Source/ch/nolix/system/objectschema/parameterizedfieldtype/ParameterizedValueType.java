//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

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
  public ContentType getFieldType() {
    return ContentType.VALUE;
  }
}
