package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.model.BackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiValueModelDto;
import ch.nolix.systemapi.midschema.model.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalValueModelDto;
import ch.nolix.systemapi.midschema.model.ReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ValueModelDto;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnTableFieldIndexCatalog;

public final class ContentModelDtoMapperHelper {
  private ContentModelDtoMapperHelper() {
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ValueModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new OptionalValueModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForMultiValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new MultiValueModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),

      //TODO: Update model
      ImmutableList.withElement(
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new OptionalReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),

      //TODO: Update model
      ImmutableList.withElement(
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new MultiReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),

      //TODO: Update model
      ImmutableList.withElement(
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new BackReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      columnTableSqlRecord.getStoredAtOneBasedIndex(6));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForOptionalBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new OptionalBackReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      columnTableSqlRecord.getStoredAtOneBasedIndex(6));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForMultiBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new MultiBackReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      columnTableSqlRecord.getStoredAtOneBasedIndex(6));
  }
}
