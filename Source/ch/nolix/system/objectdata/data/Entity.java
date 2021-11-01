//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedEntity;

//class
public abstract class Entity extends DatabaseObject implements IExtendedEntity<Entity, Property> {
	
	//method
	@Override
	public final boolean belongsToTable() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public final void delete() {
		//TODO: Implement.
	}
	
	//method
	@Override
	public final String getId() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final ITable<?, ?, ?> getParentTable() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final IContainer<Property> getRefProperties() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final String getShortDescription() {
		//TODO: Implement.
		return null;
	}
	
	//method
	public final boolean isBackReferenced() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public final boolean isDeleted() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//method
	public final boolean isReferenced() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	protected final void noteCloseDatabaseObject() {}
}
