//package declaration
package ch.nolix.system.nodedatabaserawdata.dataadapter;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.databasereader.DatabaseReader;
import ch.nolix.system.nodedatabaserawdata.databasereader.TableDefinitionLoader;
import ch.nolix.system.nodedatabaserawdata.databasewriter.DatabaseWriter;
import ch.nolix.system.rawdatabase.databaseadapter.BaseDataAdapter;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class DataAdapter extends BaseDataAdapter {

  // constant
  private static final TableDefinitionLoader TABLE_DEFINITION_LOADER = new TableDefinitionLoader();

  // static method
  public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAdapter(nodeDatabase);
  }

  // constructor
  private DataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, TABLE_DEFINITION_LOADER.loadTableDefinitionsFromDatabaseNode(nodeDatabase));
  }

  // constructor
  private DataAdapter(
      final IMutableNode<?> nodeDatabase,
      final IContainer<ITableInfo> tableInfos) {
    super(
        new DatabaseReader(nodeDatabase, tableInfos),
        new DatabaseWriter(nodeDatabase, tableInfos));
  }
}
