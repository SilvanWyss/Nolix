package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IMultiReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class MultiReferenceModel extends AbstractReferenceModel implements IMultiReferenceModel {

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
