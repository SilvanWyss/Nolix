//package declaration
package ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

//interface
public interface IParameterizedFieldTypeMapper<PPTDTO extends IParameterizedFieldTypeDto> {

  //method declaration
  IParameterizedFieldType createParameterizedFieldTypeFromDto(
    PPTDTO parameterizedFieldTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables);
}
