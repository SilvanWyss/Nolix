package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedValueType<V> extends BaseParameterizedValueType<V> {

  private ParameterizedValueType(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> ParameterizedValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedValueType<>(valueType);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.VALUE;
  }
}
