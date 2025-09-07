package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.IContentModelNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2024-09-12
 */
public final class ContentModelNodeMapper implements IContentModelNodeMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToContentModelNode(final ColumnDto columnDto) {
    final var contentModel = columnDto.contentModel();

    return mapContentModelDtoToNode(contentModel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapContentModelDtoToNode(final ContentModelDto contentModelDto) {
    final var fieldType = contentModelDto.fieldType();
    final var baseFieldType = fieldType.getBaseType();
    final var dataType = contentModelDto.dataType();

    return //
    switch (baseFieldType) {
      case BASE_VALUE_FIELD ->
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_MODEL,
          Node.withHeaderAndChildNode(
            NodeHeaderCatalog.FIELD_TYPE,
            fieldType.name()),
          Node.withHeaderAndChildNode(
            NodeHeaderCatalog.DATA_TYPE,
            dataType.name()));
      case BASE_REFERENCE ->
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_MODEL,
          Node.withHeaderAndChildNode(
            NodeHeaderCatalog.FIELD_TYPE,
            fieldType.name()),
          Node.withHeaderAndChildNode(
            NodeHeaderCatalog.DATA_TYPE,
            dataType.name()),
          Node.withHeaderAndChildNodes(
            NodeHeaderCatalog.REFERENCEABLE_TABLE_IDS,
            contentModelDto.referenceableTableIds().to(Node::withHeader)));
      case BASE_BACK_REFERENCE ->
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_MODEL,
          Node.withHeaderAndChildNode(
            NodeHeaderCatalog.FIELD_TYPE,
            fieldType.name()),
          Node.withHeaderAndChildNode(
            NodeHeaderCatalog.DATA_TYPE,
            dataType.name()),
          Node.withHeaderAndChildNode(
            NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID,

            //TODO: Re-engineer
            contentModelDto.backReferenceableColumnIds().getStoredFirst()));
      default ->
        throw InvalidArgumentException.forArgument(baseFieldType);
    };
  }
}
