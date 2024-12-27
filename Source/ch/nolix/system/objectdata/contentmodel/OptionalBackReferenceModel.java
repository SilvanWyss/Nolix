package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;

public final class OptionalBackReferenceModel<C extends IColumn>
extends AbstractBackReferenceModel<C> {

  private OptionalBackReferenceModel(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumn> OptionalBackReferenceModel<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new OptionalBackReferenceModel<>(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
