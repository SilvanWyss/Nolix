package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IColumn;

public final class BackReferenceModel extends AbstractBaseBackReferenceModel implements IBackReferenceModel {
  private BackReferenceModel(final IContainer<IColumn> backReferenceableColumns) {
    super(backReferenceableColumns);
  }

  public static BackReferenceModel forBackReferencedColumn(
    final IColumn backReferencedColumn,
    final IColumn... backReferencedColumns) {
    final var allBackReferencedColumns = ContainerView.forElementAndArray(backReferencedColumn, backReferencedColumns);

    return new BackReferenceModel(allBackReferencedColumns);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.BACK_REFERENCE;
  }
}
