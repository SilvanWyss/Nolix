package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi.IContentModelDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public final class ContentModelDtoMapper implements IContentModelDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public IContentModelDto mapJoinedColumnSqlRecordToColumnDto(final ISqlRecord joinedColumnSqlRecord) {

    final var contentTypeEntry = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.CONTENT_TYPE_INDEX);

    final var contentType = ContentType.valueOf(contentTypeEntry);

    return //
    switch (contentType) {
      case VALUE ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForValueColumn(joinedColumnSqlRecord);
      case OPTIONAL_VALUE ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(joinedColumnSqlRecord);
      case MULTI_VALUE ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiValueColumn(joinedColumnSqlRecord);
      case REFERENCE ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForReferenceColumn(joinedColumnSqlRecord);
      case OPTIONAL_REFERENCE ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(joinedColumnSqlRecord);
      case MULTI_REFERENCE ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(joinedColumnSqlRecord);
      case BACK_REFERENCE ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForBackReferenceColumn(joinedColumnSqlRecord);
      case OPTIONAL_BACK_REFERENCE ->
        ContentModelDtoMapperHelper
          .mapColumnTableSqlRecordToColumnDtoForOptionalBackReferenceColumn(joinedColumnSqlRecord);
      case MULTI_BACK_REFERENCE ->
        ContentModelDtoMapperHelper
          .mapColumnTableSqlRecordToColumnDtoForMultiBackReferenceColumn(joinedColumnSqlRecord);
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }
}
