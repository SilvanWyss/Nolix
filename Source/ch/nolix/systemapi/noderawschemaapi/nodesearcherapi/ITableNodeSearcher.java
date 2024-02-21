//package declaration
package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface ITableNodeSearcher {

  //method declaration
  IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(IMutableNode<?> tableNode, String columnName);

  //method declaration
  IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(IMutableNode<?> tableNode);

  //method declaration
  IMutableNode<?> getStoredIdNodeFromTableNode(IMutableNode<?> tableNode);

  //method declaration
  IMutableNode<?> getStoredNameNodeFromTableNode(IMutableNode<?> tableNode);

  //method declaration
  String getTableIdFromTableNode(IMutableNode<?> tableNode);

  //method declaration
  String getTableNameFromTableNode(IMutableNode<?> tableNode);
}
