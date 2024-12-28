package ch.nolix.system.noderawdata.adapter;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.datareader.DataReader;
import ch.nolix.system.noderawdata.datareader.TableDefinitionLoader;
import ch.nolix.system.noderawdata.datawriter.DataWriter;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapter;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;

public final class DataAdapter extends AbstractDataAdapter {

  private static final TableDefinitionLoader TABLE_DEFINITION_LOADER = new TableDefinitionLoader();

  private DataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, TABLE_DEFINITION_LOADER.loadTableDefinitionsFromNodeDatabase(nodeDatabase));
  }

  private DataAdapter(
    final IMutableNode<?> nodeDatabase,
    final IContainer<ITableView> tableViews) {
    super(
      new DataReader(nodeDatabase, tableViews),
      new DataWriter(nodeDatabase, tableViews));
  }

  public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new DataAdapter(nodeDatabase);
  }
}
