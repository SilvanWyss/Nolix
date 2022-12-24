//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParametrizedReferenceType<
	IMPL,
	E extends IEntity<IMPL>
>
extends IParametrizedPropertyType<IMPL> {
	
	//method declaration
	ITable<IMPL, E> getRefencedTable();
}
