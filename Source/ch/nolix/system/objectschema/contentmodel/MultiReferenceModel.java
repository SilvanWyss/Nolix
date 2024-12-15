package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class MultiReferenceModel extends AbstractReferenceModel {

  private MultiReferenceModel(final ITable referencedTable) {
    super(referencedTable);
  }

  public static MultiReferenceModel forReferencedTable(final ITable referencedTable) {
    return new MultiReferenceModel(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_REFERENCE;
  }
}
