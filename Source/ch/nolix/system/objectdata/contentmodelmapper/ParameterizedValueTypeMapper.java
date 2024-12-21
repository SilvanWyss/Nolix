package ch.nolix.system.objectdata.contentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.ValueModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;

public final class ParameterizedValueTypeMapper
implements IContentModelMapper<ValueModelDto> {

  @Override
  public IContentModel createParameterizedFieldTypeFromDto(
    final ValueModelDto parameterizedFieldTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = parameterizedFieldTypeDto.dataType().getDataTypeClass();

    return ValueModel.forValueType(valueType);
  }
}
