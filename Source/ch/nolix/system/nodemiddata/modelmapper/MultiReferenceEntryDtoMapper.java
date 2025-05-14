package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.nodemiddataapi.modelmapperapi.IMultiReferenceEntryDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public final class MultiReferenceEntryDtoMapper implements IMultiReferenceEntryDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public MultiReferenceEntryDto mapMultiReferenceEntryNodeToMultiReferenceEntryDto(
    final IMutableNode<?> multiReferenceEntryNode,
    final String tableName,
    final String entityId,
    final String multiReferenceColumnId) {

    final var referencedEntityId = multiReferenceEntryNode.getStoredChildNodeAtOneBasedIndex(1).getHeader();
    final var referencedEntityTableId = multiReferenceEntryNode.getStoredChildNodeAtOneBasedIndex(2).getHeader();

    return //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnId,
      referencedEntityId,
      referencedEntityTableId);
  }
}
