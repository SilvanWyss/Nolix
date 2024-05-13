//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

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
  public ContentType getFieldType() {
    return ContentType.MULTI_VALUE;
  }
}
