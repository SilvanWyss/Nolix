//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IParametrizedPropertyTypeHelper;

//class
public final class ParametrizedPropertyTypeHelper extends DatabaseObjectHelper
implements IParametrizedPropertyTypeHelper {
	
	//method
	@Override
	public boolean isABaseBackReferenceType(final IParametrizedPropertyType<?, ?> parametrizedPropertyType) {
		return (parametrizedPropertyType.getPropertyType().getBaseType() == BasePropertyType.BASE_BACK_REFERENCE);
	}
	
	//method
	@Override
	public boolean isABaseReferenceType(IParametrizedPropertyType<?, ?> parametrizedPropertyType) {
		return (parametrizedPropertyType.getPropertyType().getBaseType() == BasePropertyType.BASE_REFERENCE);
	}
	
	//method
	@Override
	public boolean isABaseValueType(IParametrizedPropertyType<?, ?> parametrizedPropertyType) {
		return (parametrizedPropertyType.getPropertyType().getBaseType() == BasePropertyType.BASE_VALUE);
	}
}
