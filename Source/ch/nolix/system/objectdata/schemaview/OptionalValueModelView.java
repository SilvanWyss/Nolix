package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public final class OptionalValueModelView<V> extends AbstractBaseValueModelView<V> {
  private OptionalValueModelView(final Class<V> valueType) {
    super(valueType);
  }

  public static <V2> OptionalValueModelView<V2> forValueType(final Class<V2> valueType) {
    return new OptionalValueModelView<>(valueType);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_VALUE_FIELD;
  }
}
