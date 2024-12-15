package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

public final class OptionalBackReferenceModel extends AbstractBackReferenceModel {

  private OptionalBackReferenceModel(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static OptionalBackReferenceModel forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new OptionalBackReferenceModel(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
