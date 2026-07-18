/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.modelmapper;

import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceEntryDtoMapper {
  /**
   * @param multiReferenceSqlRecord
   * @param databaseView
   * @return a new {@link MultiReferenceEntryDto} from the given
   *         multiReferenceSqlRecord using the given databaseView
   * @throws RuntimeException if the given multiReferenceSqlRecord is null.
   */
  MultiReferenceEntryDto mapMultiReferenceEntrySqlRecordToMultiReferenceEntryDto(
    ISqlRecord multiReferenceSqlRecord,
    DatabaseInfoDto databaseView);
}
