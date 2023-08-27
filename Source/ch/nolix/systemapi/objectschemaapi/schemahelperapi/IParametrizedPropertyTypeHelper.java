//package declaration
package ch.nolix.systemapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;

//interface
public interface IParametrizedPropertyTypeHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean isABaseBackReferenceType(IParameterizedPropertyType parameterizedPropertyType);
	
	//method declaration
	boolean isABaseReferenceType(IParameterizedPropertyType parameterizedPropertyType);
	
	//method declaration
	boolean isABaseValueType(IParameterizedPropertyType parameterizedPropertyType);
}
