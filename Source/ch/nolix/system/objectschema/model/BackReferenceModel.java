package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;

public final class BackReferenceModel extends AbstractBackReferenceModel implements IBackReferenceModel {

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
}
