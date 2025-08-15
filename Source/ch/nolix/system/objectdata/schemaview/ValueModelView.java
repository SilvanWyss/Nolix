package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public final class ValueModelView<V> extends AbstractBaseValueModelView<V> {

  private ValueModelView(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> ValueModelView<V2> forValueType(final Class<V2> valueType) {
    return new ValueModelView<>(valueType);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.VALUE_FIELD;
  }
}
