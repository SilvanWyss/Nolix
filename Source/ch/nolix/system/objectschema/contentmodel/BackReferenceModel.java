package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;

public final class BackReferenceModel extends AbstractBackReferenceModel {

  private BackReferenceModel(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static BackReferenceModel forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new BackReferenceModel(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.BACK_REFERENCE;
  }

  @Override
  public IContentModelDto toDto() {

    final var backReferencedColumnId = getBackReferencedColumn().getId();

    return new BackReferenceModelDto(getDataType(), backReferencedColumnId);
  }
}
