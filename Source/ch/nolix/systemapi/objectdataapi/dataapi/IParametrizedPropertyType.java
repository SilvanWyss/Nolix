//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType<IMPL> {
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable<IMPL, IEntity<IMPL>> table);
}
