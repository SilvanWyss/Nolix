package ch.nolix.system.noderawdata.adapter;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.datareader.DataReader;
import ch.nolix.system.noderawdata.datawriter.DataWriter;
import ch.nolix.system.noderawdata.schemaviewloader.TableDefinitionLoader;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapter;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;

public final class NodeDataAdapter extends AbstractDataAdapter {

  private static final TableDefinitionLoader TABLE_DEFINITION_LOADER = new TableDefinitionLoader();

  private NodeDataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, TABLE_DEFINITION_LOADER.loadTableDefinitionsFromNodeDatabase(nodeDatabase));
  }

  private NodeDataAdapter(
    final IMutableNode<?> nodeDatabase,
    final IContainer<TableViewDto> tableViews) {
    super(
      new DataReader(nodeDatabase, tableViews),
      new DataWriter(nodeDatabase, tableViews));
  }

  public static NodeDataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapter(nodeDatabase);
  }
}
