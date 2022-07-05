//package declaration
package ch.nolix.systemapi.objectdataapi.dataadapterapi;

import ch.nolix.coreapi.functionapi.mutationuniversalapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface IDataAdapter<IMPL> extends IMultiTimeChangeSaver {
	
	//method declaration
	<E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableByEntityType(final Class<E> entityType);
	
	//method declaration
	<E extends IEntity<IMPL>> IDataAdapter<IMPL> insert(E entity);
}
