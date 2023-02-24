//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType {
	
	//method declaration
	IBaseParametrizedBackReferenceType<?> asBaseParametrizedBackReferenceType();
	
	//method declaration
	IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType();
	
	//method declaration
	IBaseParametrizedValueType<?> asBaseParametrizedValueType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable<?> table);
}
