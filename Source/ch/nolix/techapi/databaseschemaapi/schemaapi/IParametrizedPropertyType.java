//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

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
	
	//method
	default boolean isAnyBackReferenceType() {
		return (getBasePropertyType() == BasePropertyType.BASE_BACK_REFERENCE);
	}
	
	//method
	default boolean isAnyControlType() {
		return (getBasePropertyType() == BasePropertyType.BASE_CONTROL_TYPE);
	}
	
	//method
	default boolean isAnyReferenceType() {
		return (getBasePropertyType() == BasePropertyType.BASE_REFERENCE);
	}
	
	//method
	default boolean isAnyValueType() {
		return (getBasePropertyType() == BasePropertyType.BASE_VALUE);
	}
	
	//method declaration
	boolean references(ITable<?, ?, ?> table);
	
	//method declaration
	boolean referencesBack(IColumn<?, ?> column);
}
