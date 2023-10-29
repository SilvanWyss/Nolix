//package declaration
package ch.nolix.system.nodedatabaserawschema.databaseinitializer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.time.moment.Time;

//class
final class InternalDatabaseInitializer {

  //method
  public void initializeDatabase(final IMutableNode<?> databaseNode) {
    databaseNode
      .setHeader(SubNodeHeaderCatalogue.DATABASE)
      .addChildNode(createDatabasePropertiesNode());
  }

  //method
  private Node createDatabasePropertiesNode() {
    return Node.withHeaderAndChildNode(SubNodeHeaderCatalogue.DATABASE_PROPERTIES, createSchemaTimestampNode());
  }

  //method
  private Node createSchemaTimestampNode() {
    return Node.withHeaderAndChildNode(
      SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP,
      Time.ofNow().getSpecification().getStoredSingleChildNode());
  }
}
