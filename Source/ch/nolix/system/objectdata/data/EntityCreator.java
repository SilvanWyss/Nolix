//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class EntityCreator {
	
	//method
	public <E extends IEntity<DataImplementation>> E createEmptyEntityFor(final ITable<DataImplementation, E> table) {
		return createEmptyEntityOf(table.getEntityClass());
	}
	
	//method
	public <E extends IEntity<DataImplementation>> E createEmptyEntityOf(final Class<E> entityType) {
		return GlobalClassHelper.createInstanceFromDefaultConstructorOf(entityType);
	}
}
