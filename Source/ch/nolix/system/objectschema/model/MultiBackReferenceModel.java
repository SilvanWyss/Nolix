package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IMultiBackReferenceModel;

public final class MultiBackReferenceModel extends AbstractBaseBackReferenceModel implements IMultiBackReferenceModel {
  private MultiBackReferenceModel(final IContainer<IColumn> backReferenceableColumns) {
    super(backReferenceableColumns);
  }

  public static MultiBackReferenceModel forBackReferencedColumn(
    final IColumn backReferencedColumn,
    final IColumn... backReferencedColumns) {
    final var allBackReferencedColumns = ContainerView.forElementAndArray(backReferencedColumn, backReferencedColumns);

    return new MultiBackReferenceModel(allBackReferencedColumns);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_BACK_REFERENCE;
  }
}
