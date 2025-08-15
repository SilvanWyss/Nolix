package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public final class MultiValueModelView<V> extends AbstractValueModelView<V> {

  private MultiValueModelView(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> MultiValueModelView<V2> forValueType(final Class<V2> valueType) {
    return new MultiValueModelView<>(valueType);
  }

  @Override
  public FieldType getContentType() {
    return FieldType.MULTI_VALUE_FIELD;
  }
}
