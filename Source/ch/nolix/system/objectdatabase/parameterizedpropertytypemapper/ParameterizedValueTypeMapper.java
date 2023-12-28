//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapperapi.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;

//class
public final class ParameterizedValueTypeMapper
implements IParameterizedPropertyTypeMapper<IBaseParameterizedValueTypeDto> {

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromDto(
    final IBaseParameterizedValueTypeDto parameterizedPropertyTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = parameterizedPropertyTypeDto.getDataType().getDataTypeClass();

    return ParameterizedValueType.forValueType(valueType);
  }
}
