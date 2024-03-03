//package declaration
package ch.nolix.system.noderawschema.nodesearcher;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class DatabasePropertiesNodeSearcher implements IDatabasePropertiesNodeSearcher {

  //method
  @Override
  public ITime getSchemaTimestampFromDatabasePropertiesNode(final IMutableNode<?> databasePropertiesNode) {

    final var schemaTimeStampNode = getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(schemaTimeStampNode);
  }

  //method
  @Override
  public IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(
    final IMutableNode<?> databasePropertiesNode) {
    return databasePropertiesNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.SCHEMA_TIMESTAMP);
  }
}
