package ch.nolix.systemapi.objectdataapi.contentmodelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;

public interface IContentModelMapper<T extends IContentModelDto> {

  IContentModel createParameterizedFieldTypeFromDto(
    T contentModelDto,
    IContainer<? extends ITable<IEntity>> referencableTables);
}
