package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtype.ParameterizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;

public final class ParameterizedValueTypeMapper
implements IParameterizedFieldTypeMapper<IBaseParameterizedValueTypeDto> {

  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromDto(
    final IBaseParameterizedValueTypeDto parameterizedFieldTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = parameterizedFieldTypeDto.getDataType().getDataTypeClass();

    return ParameterizedValueType.forValueType(valueType);
  }
}
