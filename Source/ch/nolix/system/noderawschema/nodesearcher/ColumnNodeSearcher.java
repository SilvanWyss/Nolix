package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;

public final class ColumnNodeSearcher implements IColumnNodeSearcher {

  @Override
  public boolean columnNodeContainsEntityNode(final IMutableNode<?> columnNode) {
    return columnNode.containsChildNodeWithHeader(StructureHeaderCatalogue.ENTITY);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.NAME);
  }

  @Override
  public IMutableNode<?> getStoredParameterizedFieldTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.PARAMETERIZED_FIELD_TYPE);
  }
}
