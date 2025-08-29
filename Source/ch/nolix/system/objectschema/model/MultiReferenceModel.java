package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IMultiReferenceModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class MultiReferenceModel extends AbstractBaseReferenceModel implements IMultiReferenceModel {

  private MultiReferenceModel(final IContainer<? extends ITable> referenceableTables) {
    super(referenceableTables);
  }

  public static MultiReferenceModel forReferenceableTables(final IContainer<? extends ITable> referenceableTables) {
    return new MultiReferenceModel(referenceableTables);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_REFERENCE;
  }
}
