package ch.nolix.system.objectschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschema.model.IOptionalReferenceModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class OptionalReferenceModel extends AbstractReferenceModel implements IOptionalReferenceModel {

  private OptionalReferenceModel(final ITable referencedTable) {
    super(referencedTable);
  }

  public static OptionalReferenceModel forReferencedTable(final ITable referencedTable) {
    return new OptionalReferenceModel(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }
}
