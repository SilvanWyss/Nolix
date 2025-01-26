package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public final class MultiBackReferenceModel<C extends IColumnView>
extends AbstractBackReferenceModel<C> {

  private MultiBackReferenceModel(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumnView> MultiBackReferenceModel<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new MultiBackReferenceModel<>(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }
}
