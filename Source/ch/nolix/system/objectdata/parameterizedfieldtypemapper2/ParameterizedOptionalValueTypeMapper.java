package ch.nolix.system.objectdata.parameterizedfieldtypemapper2;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedOptionalValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedOptionalValueTypeMapper implements IParameterizedFieldTypeMapper<IOptionalValue<?>> {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromField(
    final IOptionalValue<?> field,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(field));

    return ParameterizedOptionalValueType.forDataType(dataType);
  }
}
