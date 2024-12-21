package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ValueModel<V> extends AbstractValueModel<V> {

  private ValueModel(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> ValueModel<V2> forValueType(final Class<V2> valueType) {
    return new ValueModel<>(valueType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.VALUE;
  }
}
