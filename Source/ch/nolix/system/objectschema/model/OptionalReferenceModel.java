package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IOptionalReferenceModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class OptionalReferenceModel extends AbstractBaseReferenceModel implements IOptionalReferenceModel {
  private OptionalReferenceModel(final IContainer<? extends ITable> referenceableTables) {
    super(referenceableTables);
  }

  public static OptionalReferenceModel forReferenceableTables(final IContainer<? extends ITable> referenceableTables) {
    return new OptionalReferenceModel(referenceableTables);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_REFERENCE;
  }
}
