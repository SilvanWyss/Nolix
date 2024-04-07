//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedValueType;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedValueTypeMapper implements IParameterizedFieldTypeMapper<IValue<?>> {

  //constant
  private static final IFieldTool FIELD_TOOL = new FieldTool();

  //method
  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromField(
    final IValue<?> property,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(property));

    return ParameterizedValueType.forDataType(dataType);
  }
}
