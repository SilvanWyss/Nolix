package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;

public final class TableLoader {
  private TableLoader() {
  }

  public static IContainer<Table> loadTables(final ISchemaReader midSchemaReader) {
    final var midSchemaTableDtos = midSchemaReader.loadTables();

    return TableMapper.mapMidSchemaTableDtosToLoadedTables(midSchemaTableDtos);
  }
}
