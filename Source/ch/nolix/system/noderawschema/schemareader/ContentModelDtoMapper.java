package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ContentModelNodeSearcher;
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

  private static final ContentModelNodeSearcher PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER = //
  new ContentModelNodeSearcher();

  public IContentModelDto createContentModelDtoFromContentModelNode(final IMutableNode<?> contentModelNode) {

    final var contentType = getPropertyTypeFromParameterizedFieldTypeNode(contentModelNode);

    return //
    switch (contentType) {
      case VALUE ->
        new ValueModelDto(getDataTypeFromParameterizedFieldTypeNode(contentModelNode));
      case OPTIONAL_VALUE ->
        new OptionalValueModelDto(getDataTypeFromParameterizedFieldTypeNode(contentModelNode));
      case MULTI_VALUE ->
        new MultiValueModelDto(getDataTypeFromParameterizedFieldTypeNode(contentModelNode));
      case REFERENCE ->

        //TODO: Handle multiple referenced table ids
        new ReferenceModelDto(
          getDataTypeFromParameterizedFieldTypeNode(contentModelNode),
          ImmutableList.withElement(getReferencedTableIdFromParameterizedFieldTypeNode(contentModelNode)));
      case OPTIONAL_REFERENCE ->

        //TODO: Handle multiple referenced table ids
        new OptionalReferenceModelDto(
          getDataTypeFromParameterizedFieldTypeNode(contentModelNode),
          ImmutableList.withElement(getReferencedTableIdFromParameterizedFieldTypeNode(contentModelNode)));
      case MULTI_REFERENCE ->

        //TODO: Handle multiple referenced table ids
        new MultiReferenceModelDto(
          getDataTypeFromParameterizedFieldTypeNode(contentModelNode),
          ImmutableList.withElement(getReferencedTableIdFromParameterizedFieldTypeNode(contentModelNode)));
      case BACK_REFERENCE ->
        new BackReferenceModelDto(
          getDataTypeFromParameterizedFieldTypeNode(contentModelNode),
          getBackReferencedColumnIdFromParameterizedFieldTypeNode(contentModelNode));
      case OPTIONAL_BACK_REFERENCE ->
        new OptionalBackReferenceModelDto(
          getDataTypeFromParameterizedFieldTypeNode(contentModelNode),
          getBackReferencedColumnIdFromParameterizedFieldTypeNode(contentModelNode));
      case MULTI_BACK_REFERENCE ->
        new MultiBackReferenceModelDto(
          getDataTypeFromParameterizedFieldTypeNode(contentModelNode),
          getBackReferencedColumnIdFromParameterizedFieldTypeNode(contentModelNode));
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }

  private String getBackReferencedColumnIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var backReferencedColumnNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredBackReferencedColumnIdNodeFromContentModelNode(
        parameterizedFieldTypeNode);

    return backReferencedColumnNode.getSingleChildNodeHeader();
  }

  private DataType getDataTypeFromParameterizedFieldTypeNode(final IMutableNode<?> parameterizedFieldTypeNode) {

    final var dataTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredDataTypeNodeFromContentModelNode(
        parameterizedFieldTypeNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  private ContentType getPropertyTypeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var fieldTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredContentTypeNodeFromContentModelNode(
        parameterizedFieldTypeNode);

    return ContentType.fromSpecification(fieldTypeNode);
  }

  private String getReferencedTableIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var referencedTableIdNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredReferencedTableIdNodeFromContentModelNode(
        parameterizedFieldTypeNode);

    return referencedTableIdNode.getSingleChildNodeHeader();
  }
}
