//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedValueType;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedValueTypeMapper implements IParameterizedPropertyTypeMapper<IValue<?>> {

  //constant
  private static final IFieldTool PROPERTY_TOOL = new FieldTool();

  //method
  @Override
  public IParameterizedFieldType createParameterizedPropertyTypeFromProperty(
    final IValue<?> property,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(PROPERTY_TOOL.getDataType(property));

    return ParameterizedValueType.forDataType(dataType);
  }
}
