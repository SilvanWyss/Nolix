package ch.nolix.system.noderawdata.dataadapter;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.baserawdatabase.databaseadapter.BaseDataAdapter;
import ch.nolix.system.noderawdata.datareader.DataReader;
import ch.nolix.system.noderawdata.datareader.TableDefinitionLoader;
import ch.nolix.system.noderawdata.datawriter.DataWriter;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

public final class DataAdapter extends BaseDataAdapter {

  private static final TableDefinitionLoader TABLE_DEFINITION_LOADER = new TableDefinitionLoader();

  private DataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, TABLE_DEFINITION_LOADER.loadTableDefinitionsFromDatabaseNode(nodeDatabase));
  }

  private DataAdapter(
    final IMutableNode<?> nodeDatabase,
    final IContainer<ITableInfo> tableInfos) {
    super(
      new DataReader(nodeDatabase, tableInfos),
      new DataWriter(nodeDatabase, tableInfos));
  }

  public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAdapter(nodeDatabase);
  }
}
