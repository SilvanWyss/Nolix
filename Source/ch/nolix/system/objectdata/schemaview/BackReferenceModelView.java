package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public final class BackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
extends AbstractBaseBackReferenceModelView<C> {

  private BackReferenceModelView(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumnView<ITable<IEntity>>> BackReferenceModelView<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new BackReferenceModelView<>(backReferencedColumn);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.BACK_REFERENCE;
  }
}
