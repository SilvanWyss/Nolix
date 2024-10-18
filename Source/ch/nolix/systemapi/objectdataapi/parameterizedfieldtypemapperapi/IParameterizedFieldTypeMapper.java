package ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public interface IParameterizedFieldTypeMapper<PPTDTO extends IParameterizedFieldTypeDto> {

  IParameterizedFieldType createParameterizedFieldTypeFromDto(
    PPTDTO parameterizedFieldTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables);
}
