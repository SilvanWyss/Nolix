package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public final class MultiBackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
extends AbstractBackReferenceModelView<C> {

  private MultiBackReferenceModelView(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumnView<ITable<IEntity>>> MultiBackReferenceModelView<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new MultiBackReferenceModelView<>(backReferencedColumn);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_BACK_REFERENCE;
  }
}
