//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.interfaces.Named;

//interface
public interface IDatabaseConnector<C> extends Named {
	
	//abstract method
	public abstract <E extends Entity> IEntitySetConnector<E, C> getEntitySetConnector(
		EntitySet<E> entitySet
	);
	
	//TODO: Remove the old change storing mechanism.
	//abstract method
	public abstract void run(IContainer<C> commands);
	
	//abstract method
	public abstract void saveChanges(Iterable<Entity> changedEntitiesInOrder);
}
