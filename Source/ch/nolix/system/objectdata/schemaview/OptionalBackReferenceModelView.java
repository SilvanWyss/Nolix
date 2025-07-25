package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public final class OptionalBackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
extends AbstractBackReferenceModelView<C> {

  private OptionalBackReferenceModelView(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumnView<ITable<IEntity>>> OptionalBackReferenceModelView<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new OptionalBackReferenceModelView<>(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
