package ch.nolix.system.objectdata.dtotocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.OptionalValueModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalValueModelDto;

public final class OptionalValueModelMapper
implements IContentModelDtoToContentModelMapper<OptionalValueModelDto> {

  @Override
  public IContentModel mapContentModelDtoToContentModel(
    final OptionalValueModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = contentModelDto.dataType().getDataTypeClass();

    return OptionalValueModel.forValueType(valueType);
  }
}
