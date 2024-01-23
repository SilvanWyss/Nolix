//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.propertytool.PropertyTool;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedMultiValueType;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectdatabaseapi.propertytoolapi.IPropertyTool;
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
