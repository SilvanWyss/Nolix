package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;

public final class MultiBackReferenceModel<C extends IColumn>
extends AbstractBackReferenceModel<C> {

  private MultiBackReferenceModel(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumn> MultiBackReferenceModel<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new MultiBackReferenceModel<>(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }
}
