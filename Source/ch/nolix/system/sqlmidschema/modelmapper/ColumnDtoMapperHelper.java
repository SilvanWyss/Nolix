package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ValueModelDto;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnTableFieldIndexCatalog;

public final class ColumnDtoMapperHelper {

  private ColumnDtoMapperHelper() {
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForValueColumn(final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new ValueModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX))));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForOptionalValueColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new OptionalValueModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX))));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForMultiValueColumn(final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new MultiValueModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX))));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new ReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForOptionalReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new OptionalReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForMultiReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new MultiReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.REFERENCED_TABLE_ID_INDEX)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new BackReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAtOneBasedIndex(6)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForOptionalBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new OptionalBackReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAtOneBasedIndex(6)));
  }

  public static ColumnDto mapColumnTableSqlRecordToColumnDtoForMultiBackReferenceColumn(
    final ISqlRecord columnTableSqlRecord) {
    return //
    new ColumnDto(
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX),
      columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX),
      new MultiBackReferenceModelDto(
        DataType.valueOf(columnTableSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX)),
        columnTableSqlRecord.getStoredAtOneBasedIndex(6)));
  }
}
