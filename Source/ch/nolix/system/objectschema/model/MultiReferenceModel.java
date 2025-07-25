package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschema.model.IMultiReferenceModel;
import ch.nolix.systemapi.objectschema.model.ITable;

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
