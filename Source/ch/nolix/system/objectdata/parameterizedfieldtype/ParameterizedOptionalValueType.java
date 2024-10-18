package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedOptionalValueType<V> extends BaseParameterizedValueType<V> {

  private ParameterizedOptionalValueType(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> ParameterizedOptionalValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedOptionalValueType<>(valueType);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_VALUE;
  }
}
