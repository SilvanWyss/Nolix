//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IProperty<IMPL> {
	
	//method declaration
	String getBackReferencedPropertyName();
	
	//method declaration
	String getBackReferencedTableName();
	
	//method declaration
	ITable<IMPL, E> getRefBackReferencedTable();
}
