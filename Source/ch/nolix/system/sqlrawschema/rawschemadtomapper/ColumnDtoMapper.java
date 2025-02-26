package ch.nolix.system.sqlrawschema.rawschemadtomapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.ColumnTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi.IColumnDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-09-02
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapColumnTableSqlRecordToColumnDto(
    final ISqlRecord columnTableSqlRecord) {

    final var contentType = //
    ContentType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.CONTENT_TYPE_INDEX));

    return //
    switch (contentType) {
      case VALUE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForValueColumn(columnTableSqlRecord);
      case OPTIONAL_VALUE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(columnTableSqlRecord);
      case MULTI_VALUE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiValueColumn(columnTableSqlRecord);
      case REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForReferenceColumn(columnTableSqlRecord);
      case OPTIONAL_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(columnTableSqlRecord);
      case MULTI_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(columnTableSqlRecord);
      case BACK_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForBackReferenceColumn(columnTableSqlRecord);
      case OPTIONAL_BACK_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalBackReferenceColumn(columnTableSqlRecord);
      case MULTI_BACK_REFERENCE ->
        ColumnDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiBackReferenceColumn(columnTableSqlRecord);
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }
}
