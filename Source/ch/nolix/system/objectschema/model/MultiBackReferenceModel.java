package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IMultiBackReferenceModel;

public final class MultiBackReferenceModel extends AbstractBackReferenceModel implements IMultiBackReferenceModel {

  private MultiBackReferenceModel(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static MultiBackReferenceModel forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new MultiBackReferenceModel(backReferencedColumn);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_BACK_REFERENCE;
  }
}
