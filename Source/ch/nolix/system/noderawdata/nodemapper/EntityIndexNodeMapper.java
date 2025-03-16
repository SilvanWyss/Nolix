package ch.nolix.system.noderawdata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityIndexNodeMapper;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class EntityIndexNodeMapper implements IEntityIndexNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapEntityCreationDtoToEntityIndexNode(
    final EntityCreationDto entityCreationDto,
    final String tableId) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ENTITY_INDEX, entityCreationDto.id(), tableId);
  }
}
