package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IMultiValueModel;

public final class MultiValueModel<V> extends AbstractValueModel<V> implements IMultiValueModel<V> {

  private MultiValueModel(final DataType dataType) {
    super(dataType);
  }

  public static <V2> MultiValueModel<V2> forDataType(final DataType dataType) {
    return new MultiValueModel<>(dataType);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_VALUE;
  }
}
