package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public final class OptionalBackReferenceModel<C extends IColumnView>
extends AbstractBackReferenceModel<C> {

  private OptionalBackReferenceModel(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumnView> OptionalBackReferenceModel<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new OptionalBackReferenceModel<>(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
