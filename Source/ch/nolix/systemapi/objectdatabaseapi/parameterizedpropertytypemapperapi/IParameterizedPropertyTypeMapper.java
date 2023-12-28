//package declaration
package ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//interface
public interface IParameterizedPropertyTypeMapper<PPTDTO extends IParameterizedPropertyTypeDto> {

  //method declaration
  IParameterizedPropertyType createParameterizedPropertyTypeFromDto(
    PPTDTO parameterizedPropertyTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables);
}
