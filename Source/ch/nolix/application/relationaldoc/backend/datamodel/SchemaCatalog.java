package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;

public final class SchemaCatalog {

  public static final IEntityTypeSet RELATIONAL_DOC_SCHEMA = EntityTypeSet.withEntityType(
    SmartField.class,
    SmartObject.class,
    CategorizableReferenceContent.class,
    CategorizableValueContent.class,
    ConcreteReferenceContent.class,
    ConcreteValueContent.class);

  private SchemaCatalog() {
  }
}
