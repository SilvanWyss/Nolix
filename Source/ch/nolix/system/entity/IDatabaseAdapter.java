//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.skillAPI.IChangesSaver;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;
import ch.nolix.system.databaseAdapter.Entity;

//interface
public interface IDatabaseAdapter extends IChangesSaver<DatabaseAdapter>  {
	
	//abstract method
	public <V> V createValueFromSpecification(Class<V> type, BaseNode specificaiton);
	
	//abstract method
	public abstract <E extends Entity> IEntitySet<E> getRefEntitySet(Class<E> type);
	
	//abstract method
	public abstract <ES extends IEntitySet<Entity>> IContainer<ES> getRefEntitySets();
}
