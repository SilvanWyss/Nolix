package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschemaapi.nodemapperapi.IColumnNodeMapper;

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
