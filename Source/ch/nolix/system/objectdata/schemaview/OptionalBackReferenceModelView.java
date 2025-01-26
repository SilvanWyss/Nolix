package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public final class OptionalBackReferenceModelView<C extends IColumnView>
extends AbstractBackReferenceModelView<C> {

  private OptionalBackReferenceModelView(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumnView> OptionalBackReferenceModelView<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new OptionalBackReferenceModelView<>(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
