//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
final class ColumnMapper {

  //constant
  private static final ParameterizedPropertyTypeMapper PARAMETERIZED_PROPERTY_TYPE_MAPPER = //
  new ParameterizedPropertyTypeMapper();

  //method
  public IColumn createColumnFromDtoForParentTableUsingGivenReferencableTables(
    final IColumnDto columnDto,
    final Table<IEntity> parentTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    return Column.withNameAndIdAndParameterizedPropertyTypeAndParentTable(
      columnDto.getName(),
      columnDto.getId(),
      PARAMETERIZED_PROPERTY_TYPE_MAPPER.createParameterizedPropertyTypeFromDtoUsingGivenReferencableTables(
        columnDto.getParameterizedPropertyType(),
        referencableTables),
      parentTable);
  }
}
