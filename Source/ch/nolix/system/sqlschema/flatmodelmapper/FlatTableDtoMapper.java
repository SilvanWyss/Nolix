package ch.nolix.system.sqlschema.flatmodelmapper;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.sqlschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.flatmodelmapperapi.IFlatTableDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-01-18
 */
public final class FlatTableDtoMapper implements IFlatTableDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public FlatTableDto mapSqlRecordToFlatTableDto(final ISqlRecord sqlRecord) {
    return new FlatTableDto(sqlRecord.getStoredOne());
  }
}
