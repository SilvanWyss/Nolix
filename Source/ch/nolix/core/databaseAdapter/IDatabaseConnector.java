//package declaration
package ch.nolix.core.databaseAdapter;

import ch.nolix.core.skillInterfaces.Named;

//interface
public interface IDatabaseConnector extends Named {
	
	//abstract method
	public abstract <E extends Entity> IEntitySetConnector<E> getEntitySetConnector(
		EntitySet<E> entitySet
	);
	
	//abstract method
	public abstract void saveChanges(Iterable<Entity> changedEntitiesInOrder);
}
