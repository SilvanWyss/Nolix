/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseSchemaViewDtoMapper {
  /**
   * @param nodeDatabase
   * @return a new {@link DatabaseInfoDto} with the given databaseName from the
   *         given nodeDatabase
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  DatabaseInfoDto mapTableNodeToTableViewDto(IMutableNode<?> nodeDatabase);
}
