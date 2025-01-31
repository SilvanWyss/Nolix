package ch.nolix.system.noderawdata.adapter;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.datareader.DataReader;
import ch.nolix.system.noderawdata.datawriter.DataWriter;
import ch.nolix.system.noderawdata.schemaviewmodelmapper.DatabaseSchemaViewDtoMapper;
import ch.nolix.system.rawdata.adapter.AbstractDataAdapter;
import ch.nolix.systemapi.noderawdataapi.schemaviewmodelmapperapi.IDatabaseSchemaViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;

public final class NodeDataAdapter extends AbstractDataAdapter {

  private static final IDatabaseSchemaViewDtoMapper DATABASE_SCHEMA_VIEW_DTO_MAPPER = new DatabaseSchemaViewDtoMapper();

  private NodeDataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, DATABASE_SCHEMA_VIEW_DTO_MAPPER.mapTableNodeToTableViewDto(nodeDatabase));
  }

  private NodeDataAdapter(
    final IMutableNode<?> nodeDatabase,
    final DatabaseSchemaViewDto databaseSchemaView) {
    super(new DataReader(nodeDatabase, databaseSchemaView), new DataWriter(nodeDatabase, databaseSchemaView));
  }

  public static NodeDataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapter(nodeDatabase);
  }
}
