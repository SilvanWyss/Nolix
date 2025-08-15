package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IReferenceModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ReferenceModel extends AbstractReferenceModel implements IReferenceModel {

  private ReferenceModel(final ITable referencedTable) {
    super(referencedTable);
  }

  public static ReferenceModel forReferencedTable(final ITable referencedTable) {
    return new ReferenceModel(referencedTable);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.REFERENCE;
  }
}
