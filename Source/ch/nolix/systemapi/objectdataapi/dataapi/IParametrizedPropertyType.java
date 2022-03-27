//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType<IMPL> {
	
	//method declaration
	IBaseParametrizedBackReferenceType<IMPL, ?> asBaseParametrizedBackReferenceType();
	
	//method declaration
	IBaseParametrizedReferenceType<IMPL, ?> asBaseParametrizedReferenceType();
	
	//method declaration
	IBaseParametrizedValueType<IMPL, ?> asBaseParametrizedValueType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	<E extends IEntity<IMPL>> boolean referencesTable(ITable<IMPL, E> table);
}
