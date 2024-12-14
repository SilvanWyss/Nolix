package ch.nolix.system.objectdata.parameterizedfieldtypemapper2;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedValueTypeMapper implements IParameterizedFieldTypeMapper<IValue<?>> {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  @Override
  public IContentModel createParameterizedFieldTypeFromField(
    final IValue<?> field,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(field));

    return ParameterizedValueType.forDataType(dataType);
  }
}
