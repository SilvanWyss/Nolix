package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschema.model.IValueModel;

public final class ValueModel<V> extends AbstractValueModel<V> implements IValueModel<V> {

  private ValueModel(final DataType dataType) {
    super(dataType);
  }

  public static <V2> ValueModel<V2> forDataType(final DataType dataType) {
    return new ValueModel<>(dataType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.VALUE;
  }
}
