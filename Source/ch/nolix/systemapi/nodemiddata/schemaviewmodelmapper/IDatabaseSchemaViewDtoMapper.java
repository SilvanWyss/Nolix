/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseSchemaViewDtoMapper {
  /**
   * @param nodeDatabase
   * @return a new {@link DatabaseViewDto} with the given databaseName from the
   *         given nodeDatabase.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  DatabaseViewDto mapTableNodeToTableViewDto(IMutableNode<?> nodeDatabase);
}
