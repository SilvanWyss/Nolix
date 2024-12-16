package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;

public final class ColumnDtoMapper {

  public ColumnDto createColumnDtoFromSchemaColumnTableSqlRecord(final IContainer<String> schemaColumnTableSqlRecord) {

    final var contentType = ContentType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(4));

    return //
    switch (contentType) {
      case VALUE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),
          new ValueModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5))));
      case OPTIONAL_VALUE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),
          new OptionalValueModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5))));
      case MULTI_VALUE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),
          new MultiValueModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5))));
      case REFERENCE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),

          //TODO: Handle multiple referenced table ids
          new ReferenceModelDto(DataType.valueOf(
            schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5)),
            ImmutableList.withElement(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(6))));
      case OPTIONAL_REFERENCE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),

          //TODO: Handle multiple referenced table ids
          new OptionalReferenceModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5)),
            ImmutableList.withElement(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(6))));
      case MULTI_REFERENCE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),

          //TODO: Handle multiple referenced table ids
          new MultiReferenceModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5)),
            ImmutableList.withElement(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(6))));
      case BACK_REFERENCE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),
          new BackReferenceModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5)),
            schemaColumnTableSqlRecord.getStoredAt1BasedIndex(7)));
      case OPTIONAL_BACK_REFERENCE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),
          new OptionalBackReferenceModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5)),
            schemaColumnTableSqlRecord.getStoredAt1BasedIndex(7)));
      case MULTI_BACK_REFERENCE ->
        new ColumnDto(
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(1),
          schemaColumnTableSqlRecord.getStoredAt1BasedIndex(2),
          new MultiBackReferenceModelDto(
            DataType.valueOf(schemaColumnTableSqlRecord.getStoredAt1BasedIndex(5)),
            schemaColumnTableSqlRecord.getStoredAt1BasedIndex(7)));
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }
}
