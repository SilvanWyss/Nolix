package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IReferenceModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ReferenceModel extends AbstractBaseReferenceModel implements IReferenceModel {

  private ReferenceModel(final IContainer<? extends ITable> referenceableTables) {
    super(referenceableTables);
  }

  public static ReferenceModel forReferenceableTables(final IContainer<? extends ITable> referenceableTables) {
    return new ReferenceModel(referenceableTables);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.REFERENCE;
  }
}
