//package declaration
package ch.nolix.systemapi.noderawdataapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface IEntityNodeSearcher {

  //method declaration
  String getEntitySaveStampFromEntityNode(IMutableNode<?> entityNode);

  //method declaration
  IMutableNode<?> getStoredIdNodeFromEntityNode(IMutableNode<?> entityNode);

  //method declaration
  IMutableNode<?> getStoredSaveStampNodeFromEntityNode(IMutableNode<?> entityNode);
}
