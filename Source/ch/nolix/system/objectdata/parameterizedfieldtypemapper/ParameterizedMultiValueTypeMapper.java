package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.MultiValueModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;

public final class ParameterizedMultiValueTypeMapper
implements IParameterizedFieldTypeMapper<MultiValueModelDto> {

  @Override
  public IContentModel createParameterizedFieldTypeFromDto(
    final MultiValueModelDto parameterizedFieldTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = parameterizedFieldTypeDto.dataType().getDataTypeClass();

    return MultiValueModel.forValueType(valueType);
  }
}
