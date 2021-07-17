//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType<DT> {
	
	//method declaration
	Class<DT> getDataType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean referencesTable(ITable<?, ?, ?> table);
	
	//method declaration
	boolean referencesBackColumn(IColumn<?, ?> column);
}
