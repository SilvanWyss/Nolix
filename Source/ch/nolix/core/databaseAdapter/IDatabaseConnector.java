//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.interfaces.Named;

//interface
public interface IDatabaseConnector extends Named {
	
	//abstract method
	public abstract <E extends Entity> IEntitySetConnector<E> getEntitySetConnector(
		EntitySet<E> entitySet
	);
	
	//abstract method
	public abstract void saveChanges(Iterable<Entity> changedEntitiesInOrder);
}
