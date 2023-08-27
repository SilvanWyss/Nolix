//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParameterizedPropertyType {
	
	//method declaration
	IBaseParameterizedBackReferenceType<?> asBaseParametrizedBackReferenceType();
	
	//method declaration
	IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType();
	
	//method declaration
	IBaseParameterizedValueType<?> asBaseParametrizedValueType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable<?> table);
}
