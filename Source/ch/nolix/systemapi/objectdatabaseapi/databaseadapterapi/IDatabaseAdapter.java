//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseadapterapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.IMultiTimeChangeSaver;
import ch.nolix.coreapi.programstructureapi.factoryuniversalapi.EmptyCopyable;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//interface
public interface IDatabaseAdapter<IMPL> extends EmptyCopyable<IDatabaseAdapter<IMPL>>, IMultiTimeChangeSaver {
	
	//method declaration
	<E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableByEntityType(final Class<E> entityType);
	
	//method declaration
	<E extends IEntity<IMPL>> IDatabaseAdapter<IMPL> insert(E entity);
}
