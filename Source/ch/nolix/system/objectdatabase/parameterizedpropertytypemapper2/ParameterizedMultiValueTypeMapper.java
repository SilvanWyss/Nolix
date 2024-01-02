//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedMultiValueType;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;

//class
public final class ParameterizedMultiValueTypeMapper implements IParameterizedPropertyTypeMapper<IMultiValue<?>> {

  //constant
  private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromProperty(
    final IMultiValue<?> property,
    final IContainer<? extends ITable<IEntity>> referencedTables) {

    final var dataType = DataType.forType(PROPERTY_HELPER.getDataType(property));

    return ParameterizedMultiValueType.forDataType(dataType);
  }
}
