package ch.nolix.systemapi.sqlmiddataapi.modelmapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;

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
