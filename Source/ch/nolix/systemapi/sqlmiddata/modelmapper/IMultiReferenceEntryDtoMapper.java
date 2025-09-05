package ch.nolix.systemapi.sqlmiddata.modelmapper;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public interface IMultiReferenceEntryDtoMapper {
  /**
   * @param multiReferenceSqlRecord
   * @param databaseView
   * @return a new {@link MultiReferenceEntryDto} from the given
   *         multiReferenceSqlRecord using the given databaseView.
   * @throws RuntimeException if the given multiReferenceSqlRecord is null.
   */
  MultiReferenceEntryDto mapMultiReferenceEntrySqlRecordToMultiReferenceEntryDto(
    ISqlRecord multiReferenceSqlRecord,
    DatabaseViewDto databaseView);
}
