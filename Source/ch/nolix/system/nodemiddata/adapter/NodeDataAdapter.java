/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.adapter;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.middata.adapter.AbstractDataAdapter;
import ch.nolix.system.nodemiddata.datawriter.DataWriter;
import ch.nolix.system.nodemiddata.loader.DataReader;
import ch.nolix.system.nodemiddata.schemaviewmodelmapper.DatabaseSchemaViewDtoMapper;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;

/**
 * @author Silvan Wyss
 */
public final class NodeDataAdapter extends AbstractDataAdapter {
  private static final DatabaseSchemaViewDtoMapper DATABASE_SCHEMA_VIEW_DTO_MAPPER = new DatabaseSchemaViewDtoMapper();

  private NodeDataAdapter(final IMutableNode<?> nodeDatabase) {
    this(nodeDatabase, DATABASE_SCHEMA_VIEW_DTO_MAPPER.mapTableNodeToTableViewDto(nodeDatabase));
  }

  private NodeDataAdapter(final IMutableNode<?> nodeDatabase, final DatabaseInfoDto databaseSchemaView) {
    super(
      DataReader.forNodeDatabaseAndDatabaseView(nodeDatabase, databaseSchemaView),
      DataWriter.forNodeDatabaseAndDatabaseView(nodeDatabase, databaseSchemaView));
  }

  public static NodeDataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapter(nodeDatabase);
  }
}
