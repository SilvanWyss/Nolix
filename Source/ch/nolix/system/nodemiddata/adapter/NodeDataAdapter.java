package ch.nolix.system.nodemiddata.adapter;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.middata.adapter.AbstractDataAdapter;
import ch.nolix.system.nodemiddata.datareader.DataReader;
import ch.nolix.system.nodemiddata.datawriter.DataWriter;
import ch.nolix.system.nodemiddata.schemaviewmodelmapper.DatabaseSchemaViewDtoMapper;
import ch.nolix.systemapi.middataapi.midschemaview.DatabaseViewDto;
import ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi.IDatabaseSchemaViewDtoMapper;

public final class NodeDataAdapter extends AbstractDataAdapter {

  private static final IDatabaseSchemaViewDtoMapper DATABASE_SCHEMA_VIEW_DTO_MAPPER = new DatabaseSchemaViewDtoMapper();

  private NodeDataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, DATABASE_SCHEMA_VIEW_DTO_MAPPER.mapTableNodeToTableViewDto(nodeDatabase));
  }

  private NodeDataAdapter(
    final IMutableNode<?> nodeDatabase,
    final DatabaseViewDto databaseSchemaView) {
    super(new DataReader(nodeDatabase, databaseSchemaView), new DataWriter(nodeDatabase, databaseSchemaView));
  }

  public static NodeDataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapter(nodeDatabase);
  }
}
