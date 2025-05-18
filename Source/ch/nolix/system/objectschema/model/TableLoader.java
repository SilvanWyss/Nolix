package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;

public final class TableLoader {

  private TableLoader() {
  }

  public static IContainer<Table> loadTables(final ISchemaReader midSchemaReader) {

    final var midSchemaTableDtos = midSchemaReader.loadTables();

    return TableMapper.mapMidSchemaTableDtosToLoadedTables(midSchemaTableDtos);
  }
}
