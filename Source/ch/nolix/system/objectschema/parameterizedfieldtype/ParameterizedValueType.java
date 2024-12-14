package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedValueType<V> extends BaseParameterizedValueType<V> {

  private ParameterizedValueType(final DataType dataType) {
    super(dataType);
  }

  public static <V2> ParameterizedValueType<V2> forDataType(final DataType dataType) {
    return new ParameterizedValueType<>(dataType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.VALUE;
  }
}
