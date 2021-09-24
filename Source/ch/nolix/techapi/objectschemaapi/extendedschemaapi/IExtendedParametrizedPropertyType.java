//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//interface
public interface IExtendedParametrizedPropertyType<DT> extends IParametrizedPropertyType<DT> {
	
	//method
	default BasePropertyType getBasePropertyType() {
		return getPropertyType().getBaseType();
	}
	
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
