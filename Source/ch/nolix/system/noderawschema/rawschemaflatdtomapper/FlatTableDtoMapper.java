package ch.nolix.system.noderawschema.rawschemaflatdtomapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.rawschemaflatdtomapperapi.IFlatTableDtoMapper;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class FlatTableDtoMapper implements IFlatTableDtoMapper {

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  @Override
  public FlatTableDto mapTableNodeToFlatTableDto(final IMutableNode<?> tableNode) {

    final var id = TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
    final var name = TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);

    return new FlatTableDto(id, name);
  }
}
