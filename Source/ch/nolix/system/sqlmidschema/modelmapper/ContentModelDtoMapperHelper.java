package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnTableFieldIndexCatalog;

public final class ContentModelDtoMapperHelper {
  private ContentModelDtoMapperHelper() {
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.VALUE_FIELD,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.OPTIONAL_VALUE_FIELD,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForMultiValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.MULTI_VALUE_FIELD,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.REFERENCE,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),

      //TODO: Update
      ImmutableList.withElement(
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)),

      ImmutableList.createEmpty());
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.OPTIONAL_REFERENCE,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),

      //TODO: Update
      ImmutableList.withElement(
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)),

      ImmutableList.createEmpty());
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.MULTI_REFERENCE,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),

      //TODO: Update
      ImmutableList.withElement(
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)),

      ImmutableList.createEmpty());
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.BACK_REFERENCE,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      ImmutableList.createEmpty(),

      //TODO: Update
      ImmutableList.withElement(columnTableSqlRecord.getStoredAtOneBasedIndex(6)));
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForOptionalBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.OPTIONAL_BACK_REFERENCE,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      ImmutableList.createEmpty(),

      //TODO: Update
      ImmutableList.withElement(columnTableSqlRecord.getStoredAtOneBasedIndex(6)));
  }

  public static ContentModelDto mapColumnTableSqlRecordToColumnDtoForMultiBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ContentModelDto(
      FieldType.MULTI_BACK_REFERENCE,
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      ImmutableList.createEmpty(),

      //TODO: Update
      ImmutableList.withElement(columnTableSqlRecord.getStoredAtOneBasedIndex(6)));
  }
}
