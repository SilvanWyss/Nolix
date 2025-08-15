package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
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
  public FieldType getContentType() {
    return FieldType.MULTI_REFERENCE;
  }
}
