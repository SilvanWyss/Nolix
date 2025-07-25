package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschema.model.IBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IColumn;

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
