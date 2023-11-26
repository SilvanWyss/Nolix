//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.system.databaseobject.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.entitypropertyapi.mainapi.BasePropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IParameterizedPropertyTypeHelper;

//class
public final class ParameterizedPropertyTypeHelper extends DatabaseObjectHelper
implements IParameterizedPropertyTypeHelper {

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
