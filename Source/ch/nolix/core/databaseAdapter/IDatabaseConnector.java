//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;

//interface
public interface IDatabaseConnector<C> {
	
	//abstract method
	public abstract <E extends Entity> C createCommandForAddEntity(
		EntitySet<E> entitySet,
		E entity
	);
	
	//abstract method
	public abstract <E extends Entity> C createCommandForDeleteEntity(
		EntitySet<E> entitySet,
		E entity
	);
	
	//abstract method
	public abstract <E extends Entity> C createCommandForEditEntity(
		EntitySet<E> entitySet,
		E entity
	);
	
	//abstract method
	public abstract <E extends Entity> List<E> getRefEntities(EntitySet<E> entitySet);
	
	//abstract method
	public abstract <E extends Entity> E getRefEntity(EntitySet<E> entitySet, int id);
	
	//abstract method
	public abstract void run(IContainer<C> commands);
}
