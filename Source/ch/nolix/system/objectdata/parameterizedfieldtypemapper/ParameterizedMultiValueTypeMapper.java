//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtype.ParameterizedMultiValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;

//class
public final class ParameterizedMultiValueTypeMapper
implements IParameterizedFieldTypeMapper<IBaseParameterizedValueTypeDto> {

  //method
  @Override
  public IParameterizedFieldType createParameterizedPropertyTypeFromDto(
    final IBaseParameterizedValueTypeDto parameterizedPropertyTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = parameterizedPropertyTypeDto.getDataType().getDataTypeClass();

    return ParameterizedMultiValueType.forValueType(valueType);
  }
}
