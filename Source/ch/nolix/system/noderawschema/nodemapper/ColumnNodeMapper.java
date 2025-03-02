package ch.nolix.system.noderawschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class ColumnNodeMapper implements IColumnNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToColumnNode(final ColumnDto columnDto) {

    final var idNode = ColumnNodeComponentMapper.mapColumnDtoToIdNode(columnDto);
    final var nameNode = ColumnNodeComponentMapper.mapColumnDtoToNameNode(columnDto);
    final var contentModelNode = ColumnNodeComponentMapper.mapColunDtoToContentModelNode(columnDto);

    return Node.withHeaderAndChildNode(NodeHeaderCatalog.COLUMN, idNode, nameNode, contentModelNode);
  }
}
