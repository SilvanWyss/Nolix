package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ReferenceModel extends AbstractReferenceModel {

  private ReferenceModel(final ITable referencedTable) {
    super(referencedTable);
  }

  public static ReferenceModel forReferencedTable(final ITable referencedTable) {
    return new ReferenceModel(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.REFERENCE;
  }
}
