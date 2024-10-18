package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedMultiValueType<V> extends BaseParameterizedValueType<V> {

  private ParameterizedMultiValueType(final DataType dataType) {
    super(dataType);
  }

  public static <V2> ParameterizedMultiValueType<V2> forDataType(final DataType dataType) {
    return new ParameterizedMultiValueType<>(dataType);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.MULTI_VALUE;
  }
}
