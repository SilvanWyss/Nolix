//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.system.objectdata.schema.Schema;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

//class
public final class SchemaCatalogue {

  //constant
  public static final ISchema RELATIONAL_DOC_SCHEMA = Schema.withEntityType(
    AbstractableField.class,
    AbstractableObject.class,
    AbstractReferenceContent.class,
    AbstractValueContent.class,
    ConcreteReferenceContent.class,
    ConcreteValueContent.class);

  //constructor
  private SchemaCatalogue() {
  }
}
