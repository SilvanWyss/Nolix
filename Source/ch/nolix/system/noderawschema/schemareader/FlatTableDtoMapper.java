package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDto;

final class FlatTableDtoMapper {

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  public FlatTableDto createFlatTableDtoFromTableNode(final IMutableNode<?> tableNode) {

    final var id = TABLE_NODE_SEARCHER.getStoredIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
    final var name = TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();

    return new FlatTableDto(id, name);
  }
}
