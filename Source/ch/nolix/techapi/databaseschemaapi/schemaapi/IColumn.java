//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Headerable;
import ch.nolix.common.requestapi.EmptinessRequestable;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//interface
public interface IColumn<
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<? super Object>
> extends EmptinessRequestable, Headerable<C> {
	
	//method
	default BasePropertyType getBasePropertyType() {
		return getParametrizedPropertyType().getBasePropertyType();
	}
	
	//method
	default Class<?> getDataType() {
		return getParametrizedPropertyType().getDataType();
	}
	
	//method declaration
	IParametrizedPropertyType<?> getParametrizedPropertyType();
	
	//method
	default PropertyType getPropertyType() {
		return getParametrizedPropertyType().getPropertyType();
	}
	
	//method
	default boolean isAnyBackReferenceType() {
		return getParametrizedPropertyType().isAnyBackReferenceType();
	}
	
	//method
	default boolean isAnyControlColumn() {
		return getParametrizedPropertyType().isAnyControlType();
	}
	
	//method
	default boolean isAnyReferenceColumn() {
		return getParametrizedPropertyType().isAnyReferenceType();
	}
	
	//method
	default boolean isAnyValueColumn() {
		return getParametrizedPropertyType().isAnyValueType();
	}
	
	//method
	default boolean references(final ITable<?, ?, ?> table) {
		return getParametrizedPropertyType().references(table);
	}
	
	//method
	default boolean referencesBack(final IColumn<?, ?> column) {
		return getParametrizedPropertyType().referencesBack(column);
	}
	
	//method
	C setParametrizedPropertyType(PPT parametrizedPropertyType);
}
