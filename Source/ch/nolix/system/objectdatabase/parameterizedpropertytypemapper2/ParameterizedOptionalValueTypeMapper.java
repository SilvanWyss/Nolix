//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedOptionalValueType;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapper2api.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedOptionalValueTypeMapper
implements IParameterizedPropertyTypeMapper<IOptionalValue<?>> {

  //constant
  private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromProperty(
    final IOptionalValue<?> property,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(PROPERTY_HELPER.getDataType(property));

    return ParameterizedOptionalValueType.forDataType(dataType);
  }
}
