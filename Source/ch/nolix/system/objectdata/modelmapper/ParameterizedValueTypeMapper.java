package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.ValueModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.ValueModelDto;

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
