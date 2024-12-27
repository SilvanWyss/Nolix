package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IMultiBackReferenceModel;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;

public final class MultiBackReferenceModel extends AbstractBackReferenceModel implements IMultiBackReferenceModel {

  private MultiBackReferenceModel(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static MultiBackReferenceModel forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new MultiBackReferenceModel(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }

  @Override
  public IContentModelDto toDto() {

    final var backReferencedColumnId = getBackReferencedColumn().getId();

    return new MultiBackReferenceModelDto(getDataType(), backReferencedColumnId);
  }
}
