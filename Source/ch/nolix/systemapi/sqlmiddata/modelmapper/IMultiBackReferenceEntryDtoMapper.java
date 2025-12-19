package ch.nolix.systemapi.sqlmiddata.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReferenceEntryDtoMapper {
  /**
   * @param multiBackReferenceEntrySqlRecords
   * @param tableName
   * @return new {@link MultiBackReferenceEntryDto}s from the given
   *         multiBackReferenceEntrySqlRecords and tableName.
   */
  IContainer<MultiBackReferenceEntryDto> mapMultiBackReferenceEntrySqlRecordsToMultiBackReferenceEntryDtos(
    IContainer<ISqlRecord> multiBackReferenceEntrySqlRecords,
    String tableName);

  /**
   * @param multiBackReferenceEntrySqlRecord
   * @param tableName
   * @return a new {@link MultiBackReferenceEntryDto} from the given
   *         multiBackReferenceEntrySqlRecord and tableName.
   */
  MultiBackReferenceEntryDto mapMultiBackReferenceEntrySqlRecordToMultiBackReferenceEntryDto(
    ISqlRecord multiBackReferenceEntrySqlRecord, String tableName);
}
