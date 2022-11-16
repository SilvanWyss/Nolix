//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IProperty<IMPL> {
	
	//method declaration
	ITable<IMPL, E> getReferencedTable();
	
	//method declaration
	String getReferencedTableName();
}
