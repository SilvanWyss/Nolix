package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.model.MultiValueModel;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class MultiValueTypeToContentModelMapper implements IFieldToContentModelMapper<IMultiValue<?>> {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  @Override
  public IContainer<IContentModel> mapFieldToContentModels(
    final IMultiValue<?> field,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(field));

    return ImmutableList.withElement(MultiValueModel.forDataType(dataType));
  }
}
