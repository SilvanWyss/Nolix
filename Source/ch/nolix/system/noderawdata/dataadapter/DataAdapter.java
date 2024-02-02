//package declaration
package ch.nolix.system.noderawdata.dataadapter;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.baserawdatabase.databaseadapter.BaseDataAdapter;
import ch.nolix.system.noderawdata.datareader.DataReader;
import ch.nolix.system.noderawdata.datareader.TableDefinitionLoader;
import ch.nolix.system.noderawdata.datawriter.DataWriter;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class DataAdapter extends BaseDataAdapter {

  //constant
  private static final TableDefinitionLoader TABLE_DEFINITION_LOADER = new TableDefinitionLoader();

  //constructor
  private DataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, TABLE_DEFINITION_LOADER.loadTableDefinitionsFromDatabaseNode(nodeDatabase));
  }

  //constructor
  private DataAdapter(
    final IMutableNode<?> nodeDatabase,
    final IContainer<ITableInfo> tableInfos) {
    super(
      new DataReader(nodeDatabase, tableInfos),
      new DataWriter(nodeDatabase, tableInfos));
  }

  //static method
  public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAdapter(nodeDatabase);
  }
}
