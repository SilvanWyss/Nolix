//package declaration
package ch.nolix.system.sqlrawschema.columntable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class ColumnDtoMapper {

  //method
  public ColumnDto createColumnDto(final List<String> columnSystemTableSqlRecord) {
    return switch (PropertyType.valueOf(columnSystemTableSqlRecord.get(3)).getBaseType()) {
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

  //method
  private ColumnDto createColumnDtoForBaseBackReference(final List<String> columnSystemTableSqlRecord) {
    return new ColumnDto(
      columnSystemTableSqlRecord.get(0),
      columnSystemTableSqlRecord.get(1),
      new BaseParameterizedBackReferenceTypeDto(
        PropertyType.valueOf(columnSystemTableSqlRecord.get(4)),
        DataType.valueOf(columnSystemTableSqlRecord.get(5)),
        columnSystemTableSqlRecord.get(6)));
  }

  //method
  private ColumnDto createColumnDtoForBaseReference(final List<String> columnSystemTableSqlRecord) {
    return new ColumnDto(
      columnSystemTableSqlRecord.get(0),
      columnSystemTableSqlRecord.get(1),
      new BaseParameterizedReferenceTypeDto(
        PropertyType.valueOf(columnSystemTableSqlRecord.get(3)),
        DataType.valueOf(columnSystemTableSqlRecord.get(4)),
        columnSystemTableSqlRecord.get(5)));
  }

  //method
  private ColumnDto createColumnDtoForBaseValue(final List<String> columnSystemTableSqlRecord) {
    return new ColumnDto(
      columnSystemTableSqlRecord.get(0),
      columnSystemTableSqlRecord.get(1),
      new BaseParameterizedValueTypeDto(
        PropertyType.valueOf(columnSystemTableSqlRecord.get(3)),
        DataType.valueOf(columnSystemTableSqlRecord.get(4))));
  }
}
