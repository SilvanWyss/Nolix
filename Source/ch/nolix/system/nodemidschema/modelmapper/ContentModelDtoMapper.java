package ch.nolix.system.nodemidschema.modelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.ContentModelNodeSearcher;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.nodemidschema.modelmapper.IContentModelDtoMapper;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IContentModelNodeSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class ContentModelDtoMapper implements IContentModelDtoMapper {
  private static final IContentModelNodeSearcher CONTENT_MODEL_NODE_SEARCHER = new ContentModelNodeSearcher();

  /**
   * {@inheritDoc}
   */
  @Override
  public ContentModelDto mapContentModelNodeToContentModelDto(final IMutableNode<?> contentModelNode) {
    final var fieldType = CONTENT_MODEL_NODE_SEARCHER.getFieldTypeFromContentModelNode(contentModelNode);
    final var baseFieldType = fieldType.getBaseType();
    final var dataType = CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode);

    IContainer<String> referenceableTableIds;
    IContainer<String> backReferenceableColumnIds;

    switch (baseFieldType) {
      case BASE_VALUE_FIELD:
        referenceableTableIds = ImmutableList.createEmpty();
        backReferenceableColumnIds = ImmutableList.createEmpty();
        break;
      case BASE_REFERENCE:
        referenceableTableIds = //
        CONTENT_MODEL_NODE_SEARCHER.getReferenceableTableIdsFromContentModelNode(contentModelNode);

        backReferenceableColumnIds = ImmutableList.createEmpty();
        break;
      case BASE_BACK_REFERENCE:
        referenceableTableIds = ImmutableList.createEmpty();

        //TODO: Add getBackReferenceableColumnIds method to IContentModelNodeSearcher
        backReferenceableColumnIds = //
        ImmutableList.withElement(
          CONTENT_MODEL_NODE_SEARCHER.getBackReferencedColumnIdFromContentModelNode(contentModelNode));

        break;
      default:
        throw InvalidArgumentException.forArgument(baseFieldType);
    }

    return new ContentModelDto(fieldType, dataType, referenceableTableIds, backReferenceableColumnIds);
  }
}
