package ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public interface IDatabaseSchemaViewDtoMapper {

  /**
   * @param nodeDatabase
   * @return a new {@link DatabaseViewDto} with the given databaseName from
   *         the given nodeDatabase.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  DatabaseViewDto mapTableNodeToTableViewDto(IMutableNode<?> nodeDatabase);
}
