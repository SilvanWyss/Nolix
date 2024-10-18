package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedMultiValueType<V> extends BaseParameterizedValueType<V> {

  private ParameterizedMultiValueType(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> ParameterizedMultiValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedMultiValueType<>(valueType);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.MULTI_VALUE;
  }
}
