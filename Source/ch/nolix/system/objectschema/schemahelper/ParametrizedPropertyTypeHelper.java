//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IParametrizedPropertyTypeHelper;

//class
public final class ParametrizedPropertyTypeHelper extends DatabaseObjectHelper
implements IParametrizedPropertyTypeHelper {
	
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
