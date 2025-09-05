package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IMultiValueModel;

public final class MultiValueModel<V> extends AbstractBaseValueModel<V> implements IMultiValueModel<V> {
  private MultiValueModel(final DataType dataType) {
    super(dataType);
  }

  public static <V2> MultiValueModel<V2> forDataType(final DataType dataType) {
    return new MultiValueModel<>(dataType);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_VALUE_FIELD;
  }
}
