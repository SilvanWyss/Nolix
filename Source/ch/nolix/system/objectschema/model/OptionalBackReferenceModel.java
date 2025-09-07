package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IOptionalBackReferenceModel;

public final class OptionalBackReferenceModel
extends AbstractBaseBackReferenceModel
implements IOptionalBackReferenceModel {
  private OptionalBackReferenceModel(final IContainer<? extends IColumn> backReferenceableColumns) {
    super(backReferenceableColumns);
  }

  public static OptionalBackReferenceModel forBackReferenceableColumn(
    final IColumn backReferenceableColumn,
    final IColumn... backReferenceableColumns) {
    final var allBackReferenceableColumns = //
    ContainerView.forElementAndArray(backReferenceableColumn, backReferenceableColumns);

    return new OptionalBackReferenceModel(allBackReferenceableColumns);
  }

  public static OptionalBackReferenceModel forBackReferenceableColumns(
    final IContainer<? extends IColumn> backReferenceableColumns) {
    return new OptionalBackReferenceModel(backReferenceableColumns);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
  }
}
