//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseadapterapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.IMultiTimeChangeSaver;
import ch.nolix.coreapi.programstructureapi.factoryuniversalapi.EmptyCopyable;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//interface
public interface IDatabaseAdapter<DA extends IDatabaseAdapter<DA>>
extends EmptyCopyable<DA>, IMultiTimeChangeSaver {
	
	//method declaration
	<E extends IEntity> ITable<E> getOriTableByEntityType(final Class<E> entityType);
	
	//method declaration
	<E extends IEntity> DA insert(E entity);
}
