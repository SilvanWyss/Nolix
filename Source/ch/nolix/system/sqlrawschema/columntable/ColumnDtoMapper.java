package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ColumnDtoMapper {

  public ColumnDto createColumnDto(final IContainer<String> columnSystemTableSqlRecord) {
    return switch (ContentType.valueOf(columnSystemTableSqlRecord.getStoredAt1BasedIndex(4)).getBaseType()) {
      case BASE_VALUE ->
        createColumnDtoForBaseValue(columnSystemTableSqlRecord);
      case BASE_BACK_REFERENCE ->
        createColumnDtoForBaseBackReference(columnSystemTableSqlRecord);
      case BASE_REFERENCE ->
        createColumnDtoForBaseReference(columnSystemTableSqlRecord);
      default ->
        throw InvalidArgumentException.forArgumentNameAndArgument(
          "column system table record",
          columnSystemTableSqlRecord);
    };
  }

  private ColumnDto createColumnDtoForBaseBackReference(final IContainer<String> columnSystemTableSqlRecord) {
    return new ColumnDto(
      columnSystemTableSqlRecord.getStoredAt1BasedIndex(1),
      columnSystemTableSqlRecord.getStoredAt1BasedIndex(2),
      new BaseParameterizedBackReferenceTypeDto(
        ContentType.valueOf(columnSystemTableSqlRecord.getStoredAt1BasedIndex(5)),
        DataType.valueOf(columnSystemTableSqlRecord.getStoredAt1BasedIndex(6)),
        columnSystemTableSqlRecord.getStoredAt1BasedIndex(7)));
  }

  private ColumnDto createColumnDtoForBaseReference(final IContainer<String> columnSystemTableSqlRecord) {
    return new ColumnDto(
      columnSystemTableSqlRecord.getStoredAt1BasedIndex(1),
      columnSystemTableSqlRecord.getStoredAt1BasedIndex(2),
      new BaseParameterizedReferenceTypeDto(
        ContentType.valueOf(columnSystemTableSqlRecord.getStoredAt1BasedIndex(4)),
        DataType.valueOf(columnSystemTableSqlRecord.getStoredAt1BasedIndex(5)),
        columnSystemTableSqlRecord.getStoredAt1BasedIndex(6)));
  }

  private ColumnDto createColumnDtoForBaseValue(final IContainer<String> columnSystemTableSqlRecord) {
    return new ColumnDto(
      columnSystemTableSqlRecord.getStoredAt1BasedIndex(1),
      columnSystemTableSqlRecord.getStoredAt1BasedIndex(2),
      new BaseParameterizedValueTypeDto(
        ContentType.valueOf(columnSystemTableSqlRecord.getStoredAt1BasedIndex(4)),
        DataType.valueOf(columnSystemTableSqlRecord.getStoredAt1BasedIndex(5))));
  }
}
