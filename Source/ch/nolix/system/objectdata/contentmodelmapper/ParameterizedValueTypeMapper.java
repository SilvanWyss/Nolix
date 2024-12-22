package ch.nolix.system.objectdata.contentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.ValueModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;

public final class ParameterizedValueTypeMapper
implements IContentModelDtoToContentModelMapper<ValueModelDto> {

  @Override
  public IContentModel mapContentModelDtoToContentModel(
    final ValueModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = contentModelDto.dataType().getDataTypeClass();

    return ValueModel.forValueType(valueType);
  }
}
