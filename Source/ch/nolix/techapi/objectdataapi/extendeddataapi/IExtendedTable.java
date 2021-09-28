//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface IExtendedTable<
	ET extends IExtendedTable<ET, EE>,
	EE extends IExtendedEntity<EE>
> extends IExtendedDatabaseObject, ITable<ET, EE> {
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedTable<ET, EE> addEntity(final EE... entities) {
		
		for (final var e : entities) {
			addEntity(e);
		}
		
		return this;
	}
}
