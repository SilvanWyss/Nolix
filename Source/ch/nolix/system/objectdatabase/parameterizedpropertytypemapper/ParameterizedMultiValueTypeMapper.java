//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper;

//own imports
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedMultiValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapperapi.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;

//class
public final class ParameterizedMultiValueTypeMapper
implements IParameterizedPropertyTypeMapper<IBaseParameterizedValueTypeDto> {

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromDto(
    final IBaseParameterizedValueTypeDto parameterizedPropertyTypeDto) {

    final var valueType = parameterizedPropertyTypeDto.getDataType().getDataTypeClass();

    return ParameterizedMultiValueType.forValueType(valueType);
  }
}
