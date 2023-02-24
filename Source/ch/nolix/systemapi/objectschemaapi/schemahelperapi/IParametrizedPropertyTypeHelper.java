//package declaration
package ch.nolix.systemapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//interface
public interface IParametrizedPropertyTypeHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean isABaseBackReferenceType(IParametrizedPropertyType parametrizedPropertyType);
	
	//method declaration
	boolean isABaseReferenceType(IParametrizedPropertyType parametrizedPropertyType);
	
	//method declaration
	boolean isABaseValueType(IParametrizedPropertyType parametrizedPropertyType);
}
