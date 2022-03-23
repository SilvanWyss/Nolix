//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseParametrizedBackReferenceType<
	IMPL,
	C extends IColumn<IMPL>
>
extends IParametrizedPropertyType<IMPL> {
	
	//method declaration
	C getBackReferencedColumn();
}
