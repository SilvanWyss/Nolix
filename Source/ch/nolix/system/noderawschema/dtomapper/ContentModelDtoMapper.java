package ch.nolix.system.noderawschema.dtomapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ContentModelNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.dtomapperapi.IContentModelDtoMapper;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IContentModelNodeSearcher;
import ch.nolix.systemapi.rawschemaapi.dto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.ValueModelDto;

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
  public IContentModelDto mapContentModelNodeToContentModelDto(final IMutableNode<?> contentModelNode) {

    final var contentType = CONTENT_MODEL_NODE_SEARCHER.getContentTypeFromContentModelNode(contentModelNode);

    return //
    switch (contentType) {
      case VALUE ->
        new ValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case OPTIONAL_VALUE ->
        new OptionalValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case MULTI_VALUE ->
        new MultiValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case REFERENCE ->
        new ReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getReferencedTableIdsFromContentModelNode(contentModelNode));
      case OPTIONAL_REFERENCE ->
        new OptionalReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getReferencedTableIdsFromContentModelNode(contentModelNode));
      case MULTI_REFERENCE ->
        new MultiReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getReferencedTableIdsFromContentModelNode(contentModelNode));
      case BACK_REFERENCE ->
        new BackReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getBackReferencedColumnIdFromContentModelNode(contentModelNode));
      case OPTIONAL_BACK_REFERENCE ->
        new OptionalBackReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getBackReferencedColumnIdFromContentModelNode(contentModelNode));
      case MULTI_BACK_REFERENCE ->
        new MultiBackReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getBackReferencedColumnIdFromContentModelNode(contentModelNode));
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }
}
