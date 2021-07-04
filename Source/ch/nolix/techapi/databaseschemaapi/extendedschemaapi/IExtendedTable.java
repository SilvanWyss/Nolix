//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//interface
public interface IExtendedTable<
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<? super Object>
>
extends IDatabaseObject, ITable<ET, EC, EPPT> {
	
	//method
	default void assertDoesNotContainColumnWithHeader(final String header) {
		if (containsColumnWithHeader(header)) {
			throw new InvalidArgumentException(this, "contains already a column with the header '" + header + "'");
		}
	}
}
