//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseReference<

	E extends IEntity
>
extends IProperty {
	
	//method declaration
	ITable<E> getOrierencedTable();
	
	//method declaration
	String getOrierencedTableName();
}
