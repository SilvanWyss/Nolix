package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ContentModelNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IContentModelNodeSearcher;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;

public class ContentModelDtoMapper {

  private static final IContentModelNodeSearcher CONTENT_MODEL_NODE_SEARCHER = new ContentModelNodeSearcher();

  public IContentModelDto createContentModelDtoFromContentModelNode(final IMutableNode<?> contentModelNode) {

    final var contentType = getPropertyTypeFromParameterizedFieldTypeNode(contentModelNode);

    return //
    switch (contentType) {
      case VALUE ->
        new ValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case OPTIONAL_VALUE ->
        new OptionalValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case MULTI_VALUE ->
        new MultiValueModelDto(CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode));
      case REFERENCE ->

        //TODO: Handle multiple referenced table ids
        new ReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          ImmutableList.withElement(getReferencedTableIdFromParameterizedFieldTypeNode(contentModelNode)));
      case OPTIONAL_REFERENCE ->

        //TODO: Handle multiple referenced table ids
        new OptionalReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          ImmutableList.withElement(getReferencedTableIdFromParameterizedFieldTypeNode(contentModelNode)));
      case MULTI_REFERENCE ->

        //TODO: Handle multiple referenced table ids
        new MultiReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          ImmutableList.withElement(getReferencedTableIdFromParameterizedFieldTypeNode(contentModelNode)));
      case BACK_REFERENCE ->
        new BackReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          getBackReferencedColumnIdFromParameterizedFieldTypeNode(contentModelNode));
      case OPTIONAL_BACK_REFERENCE ->
        new OptionalBackReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          getBackReferencedColumnIdFromParameterizedFieldTypeNode(contentModelNode));
      case MULTI_BACK_REFERENCE ->
        new MultiBackReferenceModelDto(
          CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode),
          getBackReferencedColumnIdFromParameterizedFieldTypeNode(contentModelNode));
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }

  private String getBackReferencedColumnIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> contentModelNode) {

    final var backReferencedColumnNode = CONTENT_MODEL_NODE_SEARCHER
      .getStoredBackReferencedColumnIdNodeFromContentModelNode(
        contentModelNode);

    return backReferencedColumnNode.getSingleChildNodeHeader();
  }

  private ContentType getPropertyTypeFromParameterizedFieldTypeNode(
    final IMutableNode<?> contentModelNode) {

    final var fieldTypeNode = CONTENT_MODEL_NODE_SEARCHER
      .getStoredContentTypeNodeFromContentModelNode(
        contentModelNode);

    return ContentType.fromSpecification(fieldTypeNode);
  }

  private String getReferencedTableIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> contentModelNode) {

    final var referencedTableIdNode = CONTENT_MODEL_NODE_SEARCHER
      .getStoredReferencedTableIdNodeFromContentModelNode(
        contentModelNode);

    return referencedTableIdNode.getSingleChildNodeHeader();
  }
}
