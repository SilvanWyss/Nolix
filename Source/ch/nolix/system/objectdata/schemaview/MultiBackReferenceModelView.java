package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

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
  public ContentType getContentType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }
}
