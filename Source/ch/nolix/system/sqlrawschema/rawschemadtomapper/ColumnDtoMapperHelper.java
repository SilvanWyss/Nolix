package ch.nolix.system.sqlrawschema.rawschemadtomapper;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ValueModelDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.ColumnTableFieldIndexCatalog;

public final class ColumnDtoMapperHelper {

  private ColumnDtoMapperHelper() {
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForValueColumn(final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new ValueModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX))));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new OptionalValueModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX))));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForMultiValueColumn(final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new MultiValueModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX))));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new ReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new OptionalReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new MultiReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new BackReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAt1BasedIndex(6)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForOptionalBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new OptionalBackReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAt1BasedIndex(6)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForMultiBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new MultiBackReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAt1BasedIndex(6)));
  }
}
