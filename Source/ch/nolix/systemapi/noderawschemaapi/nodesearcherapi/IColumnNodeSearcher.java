//package declaration
package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface IColumnNodeSearcher {

  //method declaration
  boolean columnNodeContainsEntityNode(IMutableNode<?> columnNode);

  //method declaration
  IMutableNode<?> getStoredIdNodeFromColumnNode(IMutableNode<?> columnNode);

  //method declaration
  IMutableNode<?> getStoredNameNodeFromColumnNode(IMutableNode<?> columnNode);

  //method declaration
  IMutableNode<?> getStoredParameterizedPropertyTypeNodeFromColumnNode(IMutableNode<?> columnNode);
}
