//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedTable;

//class
public final class Table extends DatabaseObject implements IExtendedTable<Table, Entity, Property> {
	
	//method
	@Override
	public Table addEntity(final Entity entity) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IDatabase<?, ?, ?, ?> getParentDatabase() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IContainer<Entity> getRefAllEntities() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public Entity getRefEntityById(final String id) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {}
}
