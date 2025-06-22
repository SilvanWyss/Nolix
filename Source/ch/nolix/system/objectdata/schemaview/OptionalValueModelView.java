package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;

public final class OptionalValueModelView<V> extends AbstractValueModelView<V> {

  private OptionalValueModelView(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> OptionalValueModelView<V2> forValueType(final Class<V2> valueType) {
    return new OptionalValueModelView<>(valueType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_VALUE;
  }
}
