package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.system.objectdata.schema.Schema;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

public final class SchemaCatalogue {

  public static final ISchema RELATIONAL_DOC_SCHEMA = Schema.withEntityType(
    AbstractableField.class,
    AbstractableObject.class,
    AbstractReferenceContent.class,
    AbstractValueContent.class,
    ConcreteReferenceContent.class,
    ConcreteValueContent.class);

  private SchemaCatalogue() {
  }
}
