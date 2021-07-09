//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//interface
public interface IExtendedParametrizedPropertyType<DT> extends IParametrizedPropertyType<DT> {
	
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
	
	//method
	default boolean isIdType() {
		return (getPropertyType() == PropertyType.ID);
	}
	
	//method declaration
	IParametrizedPropertyTypeDTO toDTO();
}
