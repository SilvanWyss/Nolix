package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
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
  public ContentModelDto mapJoinedColumnSqlRecordToColumnDto(final ISqlRecord joinedColumnSqlRecord) {
    final var fieldTypeEntry = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.FIELD_TYPE_INDEX);

    final var fieldType = FieldType.valueOf(fieldTypeEntry);

    final var dataTypeString = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX);

    final var dataType = DataType.valueOf(dataTypeString);
    final var referenceableTableIdsString = joinedColumnSqlRecord.getStoredAtOneBasedIndex(6);
    ImmutableList<String> referenceableTableIds;

    if (referenceableTableIdsString != null) {
      referenceableTableIds = ImmutableList.forArray(referenceableTableIdsString.split(","));
    } else {
      referenceableTableIds = ImmutableList.createEmpty();
    }

    final var backReferenceableTableIdsString = joinedColumnSqlRecord.getStoredAtOneBasedIndex(7);
    ImmutableList<String> backReferenceableTableIds;

    if (backReferenceableTableIdsString != null) {
      backReferenceableTableIds = ImmutableList.forArray(backReferenceableTableIdsString.split(","));
    } else {
      backReferenceableTableIds = ImmutableList.createEmpty();
    }

    return new ContentModelDto(fieldType, dataType, referenceableTableIds, backReferenceableTableIds);
  }
}
