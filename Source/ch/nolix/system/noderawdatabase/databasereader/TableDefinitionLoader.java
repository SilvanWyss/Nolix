//package declaration
package ch.nolix.system.noderawdatabase.databasereader;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class TableDefinitionLoader {

  //constant
  private static final TableDefinitionMapper TABLE_DEFINITION_MAPPER = new TableDefinitionMapper();

  //method
  public IContainer<ITableInfo> loadTableDefinitionsFromDatabaseNode(final IMutableNode<?> databaseNode) {

    final var tableNodes = databaseNode.getStoredChildNodesWithHeader(SubNodeHeaderCatalogue.TABLE);

    return tableNodes.to(TABLE_DEFINITION_MAPPER::createTableDefinitionFromTableNode);
  }
}
