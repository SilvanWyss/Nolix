//package declaration
package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface IDatabaseNodeSearcher {

  //method declaration
  IMutableNode<?> getStoredColumnNodeByColumnIdFromDatabaseNode(IMutableNode<?> databaseNode, String columnId);

  //method declaration
  IMutableNode<?> getStoredDatabasePropertiesNodeFromDatabaseNode(IMutableNode<?> databaseNode);

  //method declaration
  IMutableNode<?> getStoredEntityHeadsNodeFromDatabaseNode(IMutableNode<?> databaseNode);

  //method declaration
  IMutableNode<?> getStoredTableNodeByTableIdFromDatabaseNode(
    IMutableNode<?> databaseNode,
    String tableId);

  //method declaration
  IMutableNode<?> getStoredTableNodeByTableNameFromDatabaseNode(
    IMutableNode<?> databaseNode,
    String tableName);

  //method declaration
  IContainer<? extends IMutableNode<?>> getStoredTableNodesFromDatabaseNode(IMutableNode<?> databaseNode);

  //method declaration
  int getTableNodeCount(IMutableNode<?> databaseNode);
}
