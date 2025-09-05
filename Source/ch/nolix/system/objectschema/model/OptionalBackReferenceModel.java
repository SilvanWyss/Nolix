package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IOptionalBackReferenceModel;

public final class OptionalBackReferenceModel
extends AbstractBaseBackReferenceModel
implements IOptionalBackReferenceModel {
  private OptionalBackReferenceModel(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static OptionalBackReferenceModel forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new OptionalBackReferenceModel(backReferencedColumn);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
  }
}
