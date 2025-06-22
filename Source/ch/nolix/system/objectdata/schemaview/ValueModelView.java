package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;

public final class ValueModelView<V> extends AbstractValueModelView<V> {

  private ValueModelView(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> ValueModelView<V2> forValueType(final Class<V2> valueType) {
    return new ValueModelView<>(valueType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.VALUE;
  }
}
