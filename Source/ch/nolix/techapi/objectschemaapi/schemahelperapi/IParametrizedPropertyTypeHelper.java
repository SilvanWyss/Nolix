//package declaration
package ch.nolix.techapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//interface
public interface IParametrizedPropertyTypeHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean isABaseBackReferenceType(IParametrizedPropertyType<?, ?> parametrizedPropertyType);
	
	//method declaration
	boolean isABaseReferenceType(IParametrizedPropertyType<?, ?> parametrizedPropertyType);
	
	//method declaration
	boolean isABaseValueType(IParametrizedPropertyType<?, ?> parametrizedPropertyType);
}
