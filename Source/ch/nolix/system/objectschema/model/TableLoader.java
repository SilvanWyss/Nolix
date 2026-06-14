/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;

/**
 * @author Silvan Wyss
 */
public final class TableLoader {
  private TableLoader() {
  }

  public static IWellOrderContainer<Table> loadTables(final ISchemaReader midSchemaReader) {
    final var midSchemaTableDtos = midSchemaReader.loadTables();

    return TableMapper.mapMidSchemaTableDtosToLoadedTables(midSchemaTableDtos);
  }
}
