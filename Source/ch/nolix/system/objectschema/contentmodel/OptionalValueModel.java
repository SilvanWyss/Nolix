package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
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
