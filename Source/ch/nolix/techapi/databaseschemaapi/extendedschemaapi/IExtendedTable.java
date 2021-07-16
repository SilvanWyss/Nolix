//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ReferencedArgumentException;
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
	default void assertDoesNotContainIdColumn() {
		if (containsIdColumn()) {
			throw new ArgumentHasAttributeException(this, "id column");
		}
	}
	
	//method
	default void assertIsNotReferenced() {
		if (isReferenced()) {
			throw new ReferencedArgumentException(this);
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
	default boolean containsIdColumn() {
		return getRefColumns().containsAny(IExtendedColumn::isIdColumn);
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
	IExtendedDatabase<?, ET, EC, EPPT> getParentDatabase();
	
	//method
	default EC getRefColumnByHeader(final String header) {
		return getRefColumns().getRefFirst(c -> c.hasHeader(header));
	}
	
	//method
	default LinkedList<EC> getRefReferencingColumns() {
		
		if (!belongsToDatabase()) {
			return getRefReferencingColumnsWhenDoesNotBelongToDatabase();
		}
		
		return getRefReferencingColumnsWhenBelongsToDatabase();
	}
	
	//method
	@Override
	default boolean isDeleted() {
		return IExtendedDatabaseObject.super.isDeleted();
	}
	
	//method
	default boolean isReferenced() {
		return 
		belongsToDatabase()
		&& getParentDatabase().getRefTables().containsAny(t -> t.containsColumnThatReferencesTable(this));
	}
	
	//method
	private LinkedList<EC> getRefReferencingColumnsWhenBelongsToDatabase() {
		return
		getParentDatabase().getRefTables().toFromMany(t -> t.getRefColumns().getRefSelected(c -> c.references(this)));
	}
	
	//method
	private LinkedList<EC> getRefReferencingColumnsWhenDoesNotBelongToDatabase() {
		return getRefColumns().getRefSelected(c -> c.references(this));
	}
}
