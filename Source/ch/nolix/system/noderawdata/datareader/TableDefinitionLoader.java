package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.NodeDatabaseSearcher;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

public final class TableDefinitionLoader {

  private static final NodeDatabaseSearcher DATABASE_NODE_SEARCHER = new NodeDatabaseSearcher();

  private static final TableDefinitionMapper TABLE_DEFINITION_MAPPER = new TableDefinitionMapper();

  public IContainer<ITableInfo> loadTableDefinitionsFromNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);

    return tableNodes.to(TABLE_DEFINITION_MAPPER::createTableDefinitionFromTableNode);
  }
}
