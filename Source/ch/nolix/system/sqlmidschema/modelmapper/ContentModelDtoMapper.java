package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlmidschema.modelmapper.IContentModelDtoMapper;

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
      case VALUE_FIELD ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForValueColumn(joinedColumnSqlRecord);
      case OPTIONAL_VALUE_FIELD ->
        ContentModelDtoMapperHelper.mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(joinedColumnSqlRecord);
      case MULTI_VALUE_FIELD ->
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
