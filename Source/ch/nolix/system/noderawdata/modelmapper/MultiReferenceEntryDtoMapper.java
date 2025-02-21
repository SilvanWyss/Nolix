package ch.nolix.system.noderawdata.modelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawdataapi.modelmapperapi.IMultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;

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

    final var referencedEntityId = multiReferenceEntryNode.getStoredChildNodeAt1BasedIndex(1).getHeader();
    final var referencedEntityTableId = multiReferenceEntryNode.getStoredChildNodeAt1BasedIndex(2).getHeader();

    return //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnId,
      referencedEntityId,
      referencedEntityTableId);
  }
}
