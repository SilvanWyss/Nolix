package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
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

public final class ParameterizedFieldTypeNodeMapper {

  public Node createParameterizedFieldTypeNodeFrom(final IContentModelDto parameterizedFieldType) {

    if (parameterizedFieldType instanceof ValueModelDto valueModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          valueModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          valueModelDto.dataType().name()));
    }

    if (parameterizedFieldType instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          optionalValueModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          optionalValueModelDto.dataType().name()));
    }

    if (parameterizedFieldType instanceof MultiValueModelDto multiValueModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          multiValueModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          multiValueModelDto.dataType().name()));
    }

    if (parameterizedFieldType instanceof ReferenceModelDto referenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          referenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          referenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          StructureHeaderCatalogue.REFERENCED_TABLE_ID,

          //TODO: Handle multiple referenced table ids
          referenceModelDto.referencedTableIds().getStoredOne()));
    }

    if (parameterizedFieldType instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          optionalReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          optionalReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          StructureHeaderCatalogue.REFERENCED_TABLE_ID,

          //TODO: Handle multiple referenced table ids
          optionalReferenceModelDto.referencedTableIds().getStoredOne()));
    }

    if (parameterizedFieldType instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          multiReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          multiReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          StructureHeaderCatalogue.REFERENCED_TABLE_ID,

          //TODO: Handle multiple referenced table ids
          multiReferenceModelDto.referencedTableIds().getStoredOne()));
    }

    if (parameterizedFieldType instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          backReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          backReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
          backReferenceModelDto.backReferencedColumnId()));
    }

    if (parameterizedFieldType instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          optionalBackReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          optionalBackReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
          optionalBackReferenceModelDto.backReferencedColumnId()));
    }

    if (parameterizedFieldType instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalogue.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.CONTENT_TYPE,
          multiBackReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.DATA_TYPE,
          multiBackReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
          multiBackReferenceModelDto.backReferencedColumnId()));
    }

    throw InvalidArgumentException.forArgument(parameterizedFieldType);
  }
}
