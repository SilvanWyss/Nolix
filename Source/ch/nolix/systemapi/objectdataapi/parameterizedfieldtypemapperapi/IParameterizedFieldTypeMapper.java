package ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;

public interface IParameterizedFieldTypeMapper<T extends IContentModelDto> {

  IParameterizedFieldType createParameterizedFieldTypeFromDto(
    T parameterizedFieldTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables);
}
