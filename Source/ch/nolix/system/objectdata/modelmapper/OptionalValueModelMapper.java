package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemaview.OptionalValueModelView;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;

public final class OptionalValueModelMapper
implements IContentModelDtoToContentModelMapper<OptionalValueModelDto> {

  @Override
  public IContentModelView<ITable<IEntity>> mapContentModelDtoToContentModelView(
    final OptionalValueModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = contentModelDto.dataType().getDataTypeClass();

    return OptionalValueModelView.forValueType(valueType);
  }
}
