package ch.nolix.system.objectdata.contentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.OptionalValueModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;

public final class OptionalValueModelMapper
implements IContentModelMapper<OptionalValueModelDto> {

  @Override
  public IContentModel createParameterizedFieldTypeFromDto(
    final OptionalValueModelDto parameterizedFieldTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = parameterizedFieldTypeDto.dataType().getDataTypeClass();

    return OptionalValueModel.forValueType(valueType);
  }
}
