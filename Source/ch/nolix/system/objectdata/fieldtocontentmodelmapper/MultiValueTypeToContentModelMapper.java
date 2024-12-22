package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.contentmodel.MultiValueModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class MultiValueTypeToContentModelMapper implements IFieldToContentModelMapper<IMultiValue<?>> {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  @Override
  public IContentModel mapFieldToContentModel(
    final IMultiValue<?> field,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(field));

    return MultiValueModel.forDataType(dataType);
  }
}
