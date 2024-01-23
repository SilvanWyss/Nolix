//package declaration
package ch.nolix.system.objectschema.schematool;

//own imports
import ch.nolix.system.databaseobject.databaseobjecthelper.DatabaseObjectTool;
import ch.nolix.systemapi.entitypropertyapi.mainapi.BasePropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedPropertyTypeTool;

//class
public final class ParameterizedPropertyTypeTool extends DatabaseObjectTool
implements IParameterizedPropertyTypeTool {

  //method
  @Override
  public boolean isABaseBackReferenceType(final IParameterizedPropertyType parameterizedPropertyType) {
    return (parameterizedPropertyType.getPropertyType().getBaseType() == BasePropertyType.BASE_BACK_REFERENCE);
  }

  //method
  @Override
  public boolean isABaseReferenceType(IParameterizedPropertyType parameterizedPropertyType) {
    return (parameterizedPropertyType.getPropertyType().getBaseType() == BasePropertyType.BASE_REFERENCE);
  }

  //method
  @Override
  public boolean isABaseValueType(IParameterizedPropertyType parameterizedPropertyType) {
    return (parameterizedPropertyType.getPropertyType().getBaseType() == BasePropertyType.BASE_VALUE);
  }
}
