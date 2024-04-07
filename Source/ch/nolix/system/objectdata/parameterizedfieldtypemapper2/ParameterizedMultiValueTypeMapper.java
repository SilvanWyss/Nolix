//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedMultiValueType;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedMultiValueTypeMapper implements IParameterizedFieldTypeMapper<IMultiValue<?>> {

  //constant
  private static final IFieldTool FIELD_TOOL = new FieldTool();

  //method
  @Override
  public IParameterizedFieldType createParameterizedPropertyTypeFromProperty(
    final IMultiValue<?> property,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(property));

    return ParameterizedMultiValueType.forDataType(dataType);
  }
}
