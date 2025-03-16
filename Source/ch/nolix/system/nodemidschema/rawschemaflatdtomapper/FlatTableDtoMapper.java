package ch.nolix.system.nodemidschema.rawschemaflatdtomapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.nodemidschemaapi.rawschemaflatdtomapperapi.IFlatTableDtoMapper;

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
