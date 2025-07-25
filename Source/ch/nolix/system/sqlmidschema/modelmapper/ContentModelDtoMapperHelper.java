package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ValueModelDto;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnTableFieldIndexCatalog;

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
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new OptionalReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX));
  }

  public static IContentModelDto mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new MultiReferenceModelDto(
      DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX));
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
