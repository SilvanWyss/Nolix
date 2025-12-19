package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.nodemiddata.modelmapper.IMultiBackReferenceEntryDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReferenceEntryDtoMapper implements IMultiBackReferenceEntryDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public MultiBackReferenceEntryDto mapMultiBackReferenceEntryNodeToMultiBackReferenceEntryDto(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final INode<?> multiBackReferenceEntryNode) {
    final var backReferencedEntityId = multiBackReferenceEntryNode.getStoredChildNodeAtOneBasedIndex(1).getHeader();

    final var backReferencedEntityTableId = //
    multiBackReferenceEntryNode.getStoredChildNodeAtOneBasedIndex(2).getHeader();

    return //
    new MultiBackReferenceEntryDto(
      tableName,
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId,
      backReferencedEntityTableId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<MultiBackReferenceEntryDto> mapMultiBackReferenceNodeToMultiBackReferenceEntryDtos(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final INode<?> multiBackReferenceNode) {
    final var multiBackReferenceEntryNodes = multiBackReferenceNode.getStoredChildNodes();

    return //
    multiBackReferenceEntryNodes.to(
      e -> //
      mapMultiBackReferenceEntryNodeToMultiBackReferenceEntryDto(tableName, entityId, multiBackReferenceColumnId, e));
  }
}
