//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.schemaaccessorapi.IDatabaseAccessor;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabase;

//interface
public interface IExtendedDatabase<
	ED extends IExtendedDatabase<ED, ET, EC, EPPT>,
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
>
extends IDatabase<ED, ET, EC, EPPT>, IDatabaseObject {
	
	//method declaration
	void setAccessorForActualDatabase(IDatabaseAccessor databaseAccessor);
}
