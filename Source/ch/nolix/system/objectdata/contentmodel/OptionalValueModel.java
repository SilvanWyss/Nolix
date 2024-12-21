package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class OptionalValueModel<V> extends AbstractValueModel<V> {

  private OptionalValueModel(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> OptionalValueModel<V2> forValueType(final Class<V2> valueType) {
    return new OptionalValueModel<>(valueType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_VALUE;
  }
}
