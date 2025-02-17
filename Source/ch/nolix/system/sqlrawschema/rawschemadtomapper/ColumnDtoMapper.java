package ch.nolix.system.sqlrawschema.rawschemadtomapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableReferenceDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ValueModelDto;
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
    final ISqlRecord columnTableSqlRecord,
    final IContainer<TableReferenceDto> tableReferences) {

    final var contentType = ContentType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(4));

    return //
    switch (contentType) {
      case VALUE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new ValueModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5))));
      case OPTIONAL_VALUE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new OptionalValueModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5))));
      case MULTI_VALUE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new MultiValueModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5))));
      case REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new ReferenceModelDto(DataType.valueOf(
            columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            tableReferences.to(TableReferenceDto::referencedTableId)));
      case OPTIONAL_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new OptionalReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            tableReferences.to(TableReferenceDto::referencedTableId)));
      case MULTI_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new MultiReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            tableReferences.to(TableReferenceDto::referencedTableId)));
      case BACK_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new BackReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            columnTableSqlRecord.getStoredAt1BasedIndex(6)));
      case OPTIONAL_BACK_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new OptionalBackReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            columnTableSqlRecord.getStoredAt1BasedIndex(6)));
      case MULTI_BACK_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new MultiBackReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            columnTableSqlRecord.getStoredAt1BasedIndex(6)));
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }
}
