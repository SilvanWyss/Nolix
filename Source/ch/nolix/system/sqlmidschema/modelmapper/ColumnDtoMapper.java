package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi.IColumnDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-09-02
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapJoinedColumnSqlRecordToColumnDto(final ISqlRecord joinedColumnSqlRecord) {

    final var contentTypeEntry = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.CONTENT_TYPE_INDEX);

    final var contentType = ContentType.valueOf(contentTypeEntry);

    return //
    switch (contentType) {
      case VALUE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForValueColumn(joinedColumnSqlRecord);
      case OPTIONAL_VALUE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(joinedColumnSqlRecord);
      case MULTI_VALUE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiValueColumn(joinedColumnSqlRecord);
      case REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForReferenceColumn(joinedColumnSqlRecord);
      case OPTIONAL_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(joinedColumnSqlRecord);
      case MULTI_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(joinedColumnSqlRecord);
      case BACK_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForBackReferenceColumn(joinedColumnSqlRecord);
      case OPTIONAL_BACK_REFERENCE ->
        ColumnDtoMapperHelper
          .mapColumnTableSqlRecordToColumnDtoForOptionalBackReferenceColumn(joinedColumnSqlRecord);
      case MULTI_BACK_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiBackReferenceColumn(joinedColumnSqlRecord);
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }
}
