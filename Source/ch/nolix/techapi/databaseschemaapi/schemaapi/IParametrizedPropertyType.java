//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType<DT> {
	
	//method
	default BasePropertyType getBasePropertyType() {
		return getPropertyType().getBaseType();
	}
	
	//method declaration
	Class<DT> getDataType();
	
	//method declaration
	PropertyType getPropertyType();
	
	//method declaration
	boolean references(ITable<?, ?, ?> table);
	
	//method declaration
	boolean referencesBack(IColumn<?, ?> column);
}
