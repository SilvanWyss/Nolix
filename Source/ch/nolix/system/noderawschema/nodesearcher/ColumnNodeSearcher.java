//package declaration
package ch.nolix.system.noderawschema.nodesearcher;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;

//class
public final class ColumnNodeSearcher implements IColumnNodeSearcher {

  //method
  @Override
  public boolean columnNodeContainsEntityNode(final IMutableNode<?> columnNode) {
    return columnNode.containsChildNodeWithHeader(StructureHeaderCatalogue.ENTITY);
  }

  //method
  @Override
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ID);
  }

  //method
  @Override
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.NAME);
  }

  //method
  @Override
  public IMutableNode<?> getStoredParameterizedPropertyTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE);
  }
}
