package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IOptionalValueModel;

public final class OptionalValueModel<V> extends AbstractValueModel<V> implements IOptionalValueModel<V> {

  private OptionalValueModel(final DataType dataType) {
    super(dataType);
  }

  public static <V2> OptionalValueModel<V2> forDataType(final DataType dataType) {
    return new OptionalValueModel<>(dataType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_VALUE;
  }
}
