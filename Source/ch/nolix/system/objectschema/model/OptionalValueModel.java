package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IOptionalValueModel;

public final class OptionalValueModel<V> extends AbstractBaseValueModel<V> implements IOptionalValueModel<V> {

  private OptionalValueModel(final DataType dataType) {
    super(dataType);
  }

  public static <V2> OptionalValueModel<V2> forDataType(final DataType dataType) {
    return new OptionalValueModel<>(dataType);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_VALUE_FIELD;
  }
}
