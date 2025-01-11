package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IContentModelNodeMapper;
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
 * @version 2024-09-12
 */
public final class ContentModelNodeMapper implements IContentModelNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapContentModelDtoToNode(final IContentModelDto contentModelDto) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          valueModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          valueModelDto.dataType().name()));
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          optionalValueModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          optionalValueModelDto.dataType().name()));
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          multiValueModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          multiValueModelDto.dataType().name()));
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          referenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          referenceModelDto.dataType().name()),
        Node.withHeaderAndChildNodes(
          NodeHeaderCatalog.REFERENCED_TABLE_IDS,
          referenceModelDto.referencedTableIds().to(Node::withHeader)));
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          optionalReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          optionalReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNodes(
          NodeHeaderCatalog.REFERENCED_TABLE_IDS,
          optionalReferenceModelDto.referencedTableIds().to(Node::withHeader)));
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          multiReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          multiReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNodes(
          NodeHeaderCatalog.REFERENCED_TABLE_IDS,
          multiReferenceModelDto.referencedTableIds().to(Node::withHeader)));
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          backReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          backReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID,
          backReferenceModelDto.backReferencedColumnId()));
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          optionalBackReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          optionalBackReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID,
          optionalBackReferenceModelDto.backReferencedColumnId()));
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      Node.withHeaderAndChildNode(
        NodeHeaderCatalog.CONTENT_MODEL,
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.CONTENT_TYPE,
          multiBackReferenceModelDto.getContentType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.DATA_TYPE,
          multiBackReferenceModelDto.dataType().name()),
        Node.withHeaderAndChildNode(
          NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID,
          multiBackReferenceModelDto.backReferencedColumnId()));
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
