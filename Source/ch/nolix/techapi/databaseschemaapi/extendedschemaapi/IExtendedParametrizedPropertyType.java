//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IParametrizedPropertyType;

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
}
