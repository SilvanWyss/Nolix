package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;

public final class BackReferenceModel<C extends IColumn> extends AbstractBackReferenceModel<C> {

  private BackReferenceModel(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumn> BackReferenceModel<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new BackReferenceModel<>(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.BACK_REFERENCE;
  }
}
