//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IOptionalValue<
	IMPL,
	V
>
extends Clearable, IBaseValue<IMPL, V> {
	
	//method declaration
	V getRefValue();
	
	//method declaration
	void setValue(V value);
	
	//method declaration
	void setValueFromString(String string);
}
