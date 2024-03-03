//package declaration
package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDatabasePropertiesNodeSearcher {

  //method declaration
  ITime getSchemaTimestampFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);

  //method declaration
  IMutableNode<?> getStoredSchemaTimestampNodeFromDatabasePropertiesNode(IMutableNode<?> databasePropertiesNode);
}
