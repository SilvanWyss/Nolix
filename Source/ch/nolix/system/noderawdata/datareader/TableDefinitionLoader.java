package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

public final class TableDefinitionLoader {

  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final TableDefinitionMapper TABLE_DEFINITION_MAPPER = new TableDefinitionMapper();

  public IContainer<ITableInfo> loadTableDefinitionsFromDatabaseNode(final IMutableNode<?> databaseNode) {

    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromDatabaseNode(databaseNode);

    return tableNodes.to(TABLE_DEFINITION_MAPPER::createTableDefinitionFromTableNode);
  }
}
