package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.system.objectdata.schemamodel.Schema;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;

public final class SchemaCatalog {

  public static final ISchema RELATIONAL_DOC_SCHEMA = Schema.withEntityType(
    CategorizableField.class,
    CategorizableObject.class,
    CategorizableReferenceContent.class,
    CategorizableValueContent.class,
    ConcreteReferenceContent.class,
    ConcreteValueContent.class);

  private SchemaCatalog() {
  }
}
