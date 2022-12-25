//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public final class EntityCreator {
	
	//method
	public <E extends IEntity<DataImplementation>> E createEmptyEntityFor(final ITable<DataImplementation, E> table) {
		return createEmptyEntityOf(table.getEntityType());
	}
	
	//method
	public <E extends IEntity<DataImplementation>> E createEmptyEntityOf(final Class<E> entityType) {
		return GlobalClassHelper.createInstanceFromDefaultConstructorOf(entityType);
	}
}
