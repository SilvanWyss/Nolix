//package declaration
package ch.nolix.system.noderawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class ColumnNodeSearcher {

  //method
  public boolean columnNodeContainsEntityNode(final IMutableNode<?> columnNode) {
    return columnNode.containsChildNodeWithHeader(StructureHeaderCatalogue.ENTITY);
  }

  //method
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ID);
  }

  //method
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.NAME);
  }

  //method
  public IMutableNode<?> getStoredParameterizedPropertyTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE);
  }
}
