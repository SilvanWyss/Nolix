//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

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
	default boolean isAnyReferenceType() {
		return (getBasePropertyType() == BasePropertyType.BASE_REFERENCE);
	}
	
	//method
	default boolean isAnyValueType() {
		return (getBasePropertyType() == BasePropertyType.BASE_VALUE);
	}
	
	//method declaration
	IParametrizedPropertyTypeDTO toDTO();
}
