//package declaration
package ch.nolix.system.noderawdata.nodesearcher;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalogue;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.IEntityNodeSearcher;

//class
public final class EntityNodeSearcher implements IEntityNodeSearcher {

  //method
  @Override
  public String getEntitySaveStampFromEntityNode(final IMutableNode<?> entityNode) {
    return getStoredSaveStampNodeFromEntityNode(entityNode).getSingleChildNodeHeader();
  }

  //method
  @Override
  public IMutableNode<?> getStoredIdNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX);
  }

  //method
  @Override
  public IMutableNode<?> getStoredSaveStampNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.SAVE_STAMP_INDEX);
  }
}
