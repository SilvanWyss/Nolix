//package declaration
package ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapperapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParameterizedPropertyType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//interface
public interface IParameterizedPropertyTypeMapper<PPTDTO extends IParameterizedPropertyTypeDto> {

  //method declaration
  IParameterizedPropertyType createParameterizedPropertyTypeFromDto(PPTDTO parameterizedPropertyTypeDto);
}
