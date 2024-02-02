//package declaration
package ch.nolix.systemapi.objectdataapi.parameterizedpropertytypemapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//interface
public interface IParameterizedPropertyTypeMapper<PPTDTO extends IParameterizedPropertyTypeDto> {

  //method declaration
  IParameterizedPropertyType createParameterizedPropertyTypeFromDto(
    PPTDTO parameterizedPropertyTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables);
}
