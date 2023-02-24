//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParametrizedBackReferenceType<

	C extends IColumn
>
extends IParametrizedPropertyType {
	
	//method declaration
	C getBackReferencedColumn();
}
