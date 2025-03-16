package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemaview.ValueModelView;
import ch.nolix.systemapi.midschemaapi.modelapi.ValueModelDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;

public final class ValueModelMapper implements IContentModelDtoToContentModelMapper<ValueModelDto> {

  @Override
  public IContentModelView<ITable<IEntity>> mapContentModelDtoToContentModelView(
    final ValueModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var valueType = contentModelDto.dataType().getDataTypeClass();

    return ValueModelView.forValueType(valueType);
  }
}
