//package declaration
package ch.nolix.systemapi.objectdataapi.dataadapterapi;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.core.skillapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface IDataAdapter<IMPL> extends GroupCloseable, IMultiTimeChangeSaver {
	
	//method declaration
	<E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableByEntityType(final Class<E> entityType);
	
	//method declaration
	<E extends IEntity<IMPL>> IDataAdapter<IMPL> insert(E entity);
}
