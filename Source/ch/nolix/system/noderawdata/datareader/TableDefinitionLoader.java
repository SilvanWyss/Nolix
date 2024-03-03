//package declaration
package ch.nolix.system.noderawdata.datareader;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class TableDefinitionLoader {

  //constant
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  //constant
  private static final TableDefinitionMapper TABLE_DEFINITION_MAPPER = new TableDefinitionMapper();

  //method
  public IContainer<ITableInfo> loadTableDefinitionsFromDatabaseNode(final IMutableNode<?> databaseNode) {

    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromDatabaseNode(databaseNode);

    return tableNodes.to(TABLE_DEFINITION_MAPPER::createTableDefinitionFromTableNode);
  }
}
