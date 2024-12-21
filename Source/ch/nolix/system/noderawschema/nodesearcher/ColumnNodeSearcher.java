package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IParameterizedFieldTypeNodeSearcher;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ColumnNodeSearcher implements IColumnNodeSearcher {

  private final IParameterizedFieldTypeNodeSearcher CONTENT_MODEL_NODE_SEARCHER = new ParameterizedFieldTypeNodeSearcher();

  @Override
  public boolean columnNodeContainsEntityNode(final IMutableNode<?> columnNode) {
    return columnNode.containsChildNodeWithHeader(StructureHeaderCatalogue.ENTITY);
  }

  @Override
  public String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
    return getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  @Override
  public String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
    return getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  @Override
  public ContentType getContentTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var parameterizedFieldTypeNode = getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode);

    final var fieldTypeNode = //
    CONTENT_MODEL_NODE_SEARCHER.getStoredFieldTypeNodeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode);

    return ContentType.fromSpecification(fieldTypeNode);
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
