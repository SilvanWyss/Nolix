//package declarations
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//interface
public interface IExtendedColumn<
	EC extends IColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
> extends IColumn<EC, EPPT>, IExtendedDatabaseObject {
	
	//method
	default void assertBelongsToTable() {
		if (!belongsToTable()) {
			throw new ArgumentDoesNotBelongToParentException(this, LowerCaseCatalogue.TABLE);
		}
	}

	//method
	default void assertDoesNotBelongToTable() {
		if (belongsToTable()) {
			throw new ArgumentBelongsToParentException(this, getParentTable());
		}
	}
	
	//method
	default void assertIsAnyBackReferenceColumn() {
		if (!isAnyBackReferenceColumn()) {
			throw new InvalidArgumentException(this, "is not any back reference column");
		}
	}
	
	//method
	default void assertIsAnyReferenceColumn() {
		if (!isAnyReferenceColumn()) {
			throw new InvalidArgumentException(this, "is not any reference column");
		}
	}
	
	//method
	default void assertIsNotIdColumn() {
		if (isIdColumn()) {
			throw new InvalidArgumentException(this, "is an id column");
		}
	}
	
	//method
	default boolean belongsToDatabase() {
		return (belongsToTable() && getParentTable().belongsToDatabase());
	}
	
	//method
	default BasePropertyType getBasePropertyType() {
		return getParametrizedPropertyType().getBasePropertyType();
	}
	
	//method
	default Class<?> getDataType() {
		return getParametrizedPropertyType().getDataType();
	}
	
	//method
	default IExtendedDatabase<?, ?, ?, ?> getParentDatabase() {
		return getParentTable().getParentDatabase();
	}
	
	//method declaration
	@Override
	IExtendedTable<?, ?, ?> getParentTable();
	
	//method
	default PropertyType getPropertyType() {
		return getParametrizedPropertyType().getPropertyType();
	}
	
	//method
	default boolean isAnyBackReferenceColumn() {
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
	default boolean isDeleted() {
		return IExtendedDatabaseObject.super.isDeleted();
	}
	
	//method
	default boolean isIdColumn() {
		return getParametrizedPropertyType().isIdType();
	}
	
	//method
	default boolean references(final ITable<?, ?, ?> table) {
		return getParametrizedPropertyType().references(table);
	}
	
	//method
	default boolean referencesBack(final IColumn<?, ?> column) {
		return getParametrizedPropertyType().referencesBack(column);
	}
}
