package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IOptionalBackReferenceModel;

public final class OptionalBackReferenceModel
extends AbstractBaseBackReferenceModel
implements IOptionalBackReferenceModel {
  private OptionalBackReferenceModel(final IContainer<IColumn> backReferenceableColumns) {
    super(backReferenceableColumns);
  }

  public static OptionalBackReferenceModel forBackReferencedColumn(
    final IColumn backReferencedColumn,
    final IColumn... backReferencedColumns) {
    final var allBackReferencedColumns = ContainerView.forElementAndArray(backReferencedColumn, backReferencedColumns);

    return new OptionalBackReferenceModel(allBackReferencedColumns);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
  }
}
