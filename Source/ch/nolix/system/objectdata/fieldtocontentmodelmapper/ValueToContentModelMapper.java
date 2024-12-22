package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.contentmodel.ValueModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ValueToContentModelMapper implements IFieldToContentModelMapper<IValue<?>> {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  @Override
  public IContentModel mapFieldToContentModel(
    final IValue<?> field,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(field));

    return ValueModel.forDataType(dataType);
  }
}
