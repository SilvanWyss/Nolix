//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface IExtendedTable<
	ET extends IExtendedTable<ET, EE, P>,
	EE extends IExtendedEntity<EE, P>,
	P extends IProperty<P>
> extends ITable<ET, EE, P>, IExtendedDatabaseObject {
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedTable<ET, EE, P> addEntity(final EE... entities) {
		
		for (final var e : entities) {
			addEntity(e);
		}
		
		return this;
	}
	
	//method
	@Override
	default boolean isLinkedWithRealDatabase() {
		return getParentDatabase().isLinkedWithRealDatabase();
	}
}
