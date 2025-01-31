package ch.nolix.systemapi.noderawdataapi.schemaviewmodelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public interface IDatabaseSchemaViewDtoMapper {

  /**
   * @param nodeDatabase
   * @return a new {@link DatabaseSchemaViewDto} with the given databaseName from
   *         the given nodeDatabase.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  DatabaseSchemaViewDto mapTableNodeToTableViewDto(IMutableNode<?> nodeDatabase);
}
