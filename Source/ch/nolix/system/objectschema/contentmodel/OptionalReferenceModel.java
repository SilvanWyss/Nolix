package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class OptionalReferenceModel extends AbstractReferenceModel {

  private OptionalReferenceModel(final ITable referencedTable) {
    super(referencedTable);
  }

  public static OptionalReferenceModel forReferencedTable(final ITable referencedTable) {
    return new OptionalReferenceModel(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }
}
