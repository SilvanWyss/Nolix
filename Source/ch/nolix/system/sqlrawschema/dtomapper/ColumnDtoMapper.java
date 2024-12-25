package ch.nolix.system.sqlrawschema.dtomapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;
import ch.nolix.systemapi.sqlrawschemaapi.dtomapperapi.IColumnDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-09-02
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapColumnTableSqlRecordToColumnDto(final IContainer<String> columnTableSqlRecord) {

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

          //TODO: Handle multiple referenced table ids
          new ReferenceModelDto(DataType.valueOf(
            columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            ImmutableList.withElement(columnTableSqlRecord.getStoredAt1BasedIndex(6))));
      case OPTIONAL_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),

          //TODO: Handle multiple referenced table ids
          new OptionalReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            ImmutableList.withElement(columnTableSqlRecord.getStoredAt1BasedIndex(6))));
      case MULTI_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),

          //TODO: Handle multiple referenced table ids
          new MultiReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            ImmutableList.withElement(columnTableSqlRecord.getStoredAt1BasedIndex(6))));
      case BACK_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new BackReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            columnTableSqlRecord.getStoredAt1BasedIndex(7)));
      case OPTIONAL_BACK_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new OptionalBackReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            columnTableSqlRecord.getStoredAt1BasedIndex(7)));
      case MULTI_BACK_REFERENCE ->
        new ColumnDto(
          columnTableSqlRecord.getStoredAt1BasedIndex(1),
          columnTableSqlRecord.getStoredAt1BasedIndex(2),
          new MultiBackReferenceModelDto(
            DataType.valueOf(columnTableSqlRecord.getStoredAt1BasedIndex(5)),
            columnTableSqlRecord.getStoredAt1BasedIndex(7)));
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }
}