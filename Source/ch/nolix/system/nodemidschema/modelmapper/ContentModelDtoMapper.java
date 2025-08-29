package ch.nolix.system.nodemidschema.modelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.ContentModelNodeSearcher;
import ch.nolix.systemapi.midschema.model.BackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiValueModelDto;
import ch.nolix.systemapi.midschema.model.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalValueModelDto;
import ch.nolix.systemapi.midschema.model.ReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ValueModelDto;
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
  public IContentModelDto mapContentModelNodeToContentModelDto(final IMutableNode<?> contentModelNode) {

    final var fieldType = CONTENT_MODEL_NODE_SEARCHER.getFieldTypeFromContentModelNode(contentModelNode);

    return //
    switch (fieldType) {
      case VALUE_FIELD ->
        new ValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case OPTIONAL_VALUE_FIELD ->
        new OptionalValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case MULTI_VALUE_FIELD ->
        new MultiValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case REFERENCE ->
        new ReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getReferenceableTableIdsFromContentModelNode(contentModelNode));
      case OPTIONAL_REFERENCE ->
        new OptionalReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getReferenceableTableIdsFromContentModelNode(contentModelNode));
      case MULTI_REFERENCE ->
        new MultiReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          CONTENT_MODEL_NODE_SEARCHER.getReferenceableTableIdsFromContentModelNode(contentModelNode));
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
        throw InvalidArgumentException.forArgument(fieldType);
    };
  }
}
