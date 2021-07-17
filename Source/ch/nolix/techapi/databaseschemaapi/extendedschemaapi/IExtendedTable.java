//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
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
	default void assertContainsIdColumn() {
		if (!containsIdColumn()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "id column");
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
	default boolean containsColumnBackReferencedByColumn(final IColumn<?, ?> column) {
		
		//For a better performance, this check, that is theoretically not necessary, excludes many cases.
		if (
			column.getParametrizedPropertyType().getPropertyType().getBaseType() !=
			BasePropertyType.BASE_BACK_REFERENCE
		) {
			return false;
		}
		
		return getRefColumns().containsAny(c -> column.getParametrizedPropertyType().referencesBackColumn(c));
	}
	
	//method
	default boolean containsColumnThatReferencesBackColumn(final IColumn<?, ?> column) {
		
		//For a better performance, this check, that is theoretically not necessary, excludes many cases.
		if (column.getParametrizedPropertyType().getPropertyType().getBaseType() != BasePropertyType.BASE_REFERENCE) {
			return false;
		}
		
		return getRefColumns().containsAny(c -> c.referencesBack(column));
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
	default LinkedList<EC> getRefBackReferencingColumns() {
		
		if (!belongsToDatabase()) {
			return getRefBackReferencingColumnsWhenDoesNotBelongToDatabase();
		}
		
		return getRefBackReferencingColumnsWhenBelongsToDatabase();
	}
	
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
	private LinkedList<EC> getRefBackReferencingColumnsWhenBelongsToDatabase() {
		
		final var columns = getParentDatabase().getRefTables().toFromMany(ET::getRefColumns);
		
		return getRefColumns().getRefSelected(c -> columns.containsAny(c2 -> c2.referencesBack(c)));
	}
	
	//method
	private LinkedList<EC> getRefBackReferencingColumnsWhenDoesNotBelongToDatabase() {
		
		final var columns = getRefColumns();
		
		return getRefColumns().getRefSelected(c -> columns.containsAny(c2 -> c2.referencesBack(c)));
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
