//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper.ParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
final class ColumnMapper {

  //constant
  private static final ParameterizedPropertyTypeMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ParameterizedPropertyTypeMapper();

  //method
  public IColumn createColumnFromDtoForParentTableUsingGivenReferencableTables(
    final IColumnDto columnDto,
    final Table<IEntity> parentTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    return Column.withNameAndIdAndParameterizedPropertyTypeAndParentTable(
      columnDto.getName(),
      columnDto.getId(),
      PARAMETERIZED_FIELD_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
        columnDto.getParameterizedFieldType(),
        referencableTables),
      parentTable);
  }
}
