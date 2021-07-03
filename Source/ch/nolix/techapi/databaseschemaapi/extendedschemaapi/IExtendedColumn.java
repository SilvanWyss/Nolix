//package declarations
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;

//interface
public interface IExtendedColumn<
	EC extends IColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<? super Object>
>
extends IColumn<EC, EPPT>, IDatabaseObject {
	
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
}
