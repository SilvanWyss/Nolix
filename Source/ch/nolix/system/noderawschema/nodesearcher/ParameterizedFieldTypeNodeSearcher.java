package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IParameterizedFieldTypeNodeSearcher;

public final class ParameterizedFieldTypeNodeSearcher implements IParameterizedFieldTypeNodeSearcher {

  @Override
  public IMutableNode<?> getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {
    return parameterizedFieldTypeNode
      .getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
  }

  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {
    return parameterizedFieldTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.DATA_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {
    return parameterizedFieldTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.FIELD_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {
    return parameterizedFieldTypeNode
      .getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.REFERENCED_TABLE_ID);
  }
}
