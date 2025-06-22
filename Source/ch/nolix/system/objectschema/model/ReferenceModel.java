package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class ReferenceModel extends AbstractReferenceModel implements IReferenceModel {

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
