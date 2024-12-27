package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IMultiValueModel;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;

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

  @Override
  public IContentModelDto toDto() {
    return new MultiValueModelDto(getDataType());
  }
}
