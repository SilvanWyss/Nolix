package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedOptionalValueType<V> extends BaseParameterizedValueType<V> {

  private ParameterizedOptionalValueType(final DataType dataType) {
    super(dataType);
  }

  public static <V2> ParameterizedOptionalValueType<V2> forDataType(final DataType dataType) {
    return new ParameterizedOptionalValueType<>(dataType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_VALUE;
  }
}
