package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class MultiValueModel<V> extends AbstractValueModel<V> {

  private MultiValueModel(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> MultiValueModel<V2> forValueType(final Class<V2> valueType) {
    return new MultiValueModel<>(valueType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_VALUE;
  }
}
