package ch.nolix.system.noderawdata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IMultiReferenceEntryNodeMapper;
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public final class MultiReferenceEntryNodeMapper implements IMultiReferenceEntryNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapMultiReferenceEntryDtoToMultiReferenceEntryNode(

    final MultiReferenceEntryDto multiReferenceEntryDto) {

    final var referencedEntityId = multiReferenceEntryDto.referencedEntityId();
    final var referencedEntityTableId = multiReferenceEntryDto.referencedEntityTableId();

    return Node.withChildNode(Node.withHeader(referencedEntityId), Node.withHeader(referencedEntityTableId));
  }
}
