//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.propertytool.PropertyTool;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedMultiValueType;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IPropertyTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedMultiValueTypeMapper implements IParameterizedPropertyTypeMapper<IMultiValue<?>> {

  //constant
  private static final IPropertyTool PROPERTY_TOOL = new PropertyTool();

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromProperty(
    final IMultiValue<?> property,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(PROPERTY_TOOL.getDataType(property));

    return ParameterizedMultiValueType.forDataType(dataType);
  }
}
