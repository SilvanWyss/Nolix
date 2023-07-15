//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParametrizedReferenceType<

	E extends IEntity
>
extends IParametrizedPropertyType {
	
	//method declaration
	ITable<E> getStoredencedTable();
}
