//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//interface
public interface IExtendedTable<
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
> extends IExtendedDatabaseObject, ITable<ET, EC, EPPT> {
	
	//method
	default void assertContainsColumn(final IColumn<?, ?> column) {
		if (!containsColumn(column)) {
			throw new ArgumentDoesNotContainElementException(this, column);
		}
	}
	
	//method
	default void assertDoesNotContainColumn(final IColumn<?, ?> column) {
		if (containsColumn(column)) {
			throw new ArgumentContainsElementException(this, column);
		}
	}
	
	//method
	default void assertDoesNotContainColumnWithHeader(final String header) {
		if (containsColumnWithHeader(header)) {
			throw new InvalidArgumentException(this, "contains already a column with the header '" + header + "'");
		}
	}
	
	//method
	default boolean containsColumn(final IColumn<?, ?> column) {
		return getRefColumns().contains(column);
	}
	
	//method
	default boolean containsColumnThatReferencesTable(final ITable<?, ?, ?> table) {
		return getRefColumns().containsAny(c -> c.references(table));
	}
	
	//method
	default boolean containsColumnWithHeader(final String header) {
		return getRefColumns().containsAny(c -> c.hasHeader(header));
	}
	
	//method
	default void deleteColumnByHeader(final String header) {
		getRefColumnByHeader(header).delete();
	}
	
	//method
	default int getColumnCount() {
		return getRefColumns().getElementCount();
	}
	
	//method declaration
	@Override
	IExtendedDatabase<?, ?, ?, ?> getParentDatabase();
	
	//method
	default EC getRefColumnByHeader(final String header) {
		return getRefColumns().getRefFirst(c -> c.hasHeader(header));
	}
	
	//method
	@Override
	default boolean isDeleted() {
		return IExtendedDatabaseObject.super.isDeleted();
	}
}
