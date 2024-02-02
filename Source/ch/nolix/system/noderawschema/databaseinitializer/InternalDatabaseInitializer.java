//package declaration
package ch.nolix.system.noderawschema.databaseinitializer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.time.moment.Time;

//class
final class InternalDatabaseInitializer {

  //method
  public void initializeDatabase(final IMutableNode<?> databaseNode) {
    databaseNode
      .setHeader(SubNodeHeaderCatalogue.DATABASE)
      .addChildNode(createDatabasePropertiesNode(), createEntityHeadsNode());
  }

  //method
  private Node createDatabasePropertiesNode() {
    return Node.withHeaderAndChildNode(SubNodeHeaderCatalogue.DATABASE_PROPERTIES, createSchemaTimestampNode());
  }

  //method
  private Node createSchemaTimestampNode() {
    return //
    Node.withHeaderAndChildNode(
      SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP,
      Time.ofNow().getSpecification().getStoredSingleChildNode());
  }

  //method
  private INode<?> createEntityHeadsNode() {
    return Node.withHeader(SubNodeHeaderCatalogue.ENTITY_HEADS);
  }
}
