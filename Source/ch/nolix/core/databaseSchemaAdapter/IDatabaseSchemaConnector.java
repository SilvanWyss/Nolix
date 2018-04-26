//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;

//interface
public interface IDatabaseSchemaConnector<C> {
	
	//abstract method
	public abstract C createCommandForAdd(EntityType<Entity> entityType);
	
	//abstract method
	public abstract C createCommandForDelete(EntitySet entitySet);
	
	//abstract method
	public IEntitySetConnector<C> getEntitySetConnector(final EntitySet entitySet);
	
	//abstract method
	//public abstract List<EntitySet> getEntitySets();
	
	//abstract method
	public abstract void run(Iterable<C> commands);
}
