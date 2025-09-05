package ch.nolix.system.nodemiddata.adapter;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.middata.adapter.AbstractDataAdapter;
import ch.nolix.system.nodemiddata.datareader.DataReader;
import ch.nolix.system.nodemiddata.datawriter.DataWriter;
import ch.nolix.system.nodemiddata.schemaviewmodelmapper.DatabaseSchemaViewDtoMapper;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper.IDatabaseSchemaViewDtoMapper;

public final class NodeDataAdapter extends AbstractDataAdapter {
  private static final IDatabaseSchemaViewDtoMapper DATABASE_SCHEMA_VIEW_DTO_MAPPER = new DatabaseSchemaViewDtoMapper();

  private NodeDataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, DATABASE_SCHEMA_VIEW_DTO_MAPPER.mapTableNodeToTableViewDto(nodeDatabase));
  }

  private NodeDataAdapter(final IMutableNode<?> nodeDatabase, final DatabaseViewDto databaseSchemaView) {
    super(
      DataReader.forNodeDatabaseAndDatabaseView(nodeDatabase, databaseSchemaView),
      DataWriter.forNodeDatabaseAndDatabaseView(nodeDatabase, databaseSchemaView));
  }

  public static NodeDataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapter(nodeDatabase);
  }
}
