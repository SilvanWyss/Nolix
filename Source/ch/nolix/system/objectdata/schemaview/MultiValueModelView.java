package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

public final class MultiValueModelView<V> extends AbstractValueModelView<V> {

  private MultiValueModelView(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> MultiValueModelView<V2> forValueType(final Class<V2> valueType) {
    return new MultiValueModelView<>(valueType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_VALUE;
  }
}
