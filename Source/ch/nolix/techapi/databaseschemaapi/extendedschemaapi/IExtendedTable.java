//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.ITableDTO;

//interface
public interface IExtendedTable<
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
>
extends IDatabaseObject, ITable<ET, EC, EPPT> {
	
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
	
	//method declaration
	IFlatTableDTO getFlatDTO();
	
	//method declaration
	ITableDTO toDTO();
}
