package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IColumn;

public final class BackReferenceModel extends AbstractBaseBackReferenceModel implements IBackReferenceModel {
  private BackReferenceModel(final IContainer<? extends IColumn> backReferenceableColumns) {
    super(backReferenceableColumns);
  }

  public static BackReferenceModel forBackReferenceableColumn(
    final IColumn backReferenceableColumn,
    final IColumn... backReferenceableColumns) {
    final var allBackReferenceableColumns = //
    ContainerView.forElementAndArray(backReferenceableColumn, backReferenceableColumns);

    return new BackReferenceModel(allBackReferenceableColumns);
  }

  public static BackReferenceModel forBackReferenceableColumns(
    final IContainer<? extends IColumn> backReferenceableColumns) {
    return new BackReferenceModel(backReferenceableColumns);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.BACK_REFERENCE;
  }
}
