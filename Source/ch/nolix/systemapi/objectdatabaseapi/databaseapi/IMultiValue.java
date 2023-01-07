//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IMultiValue<
	IMPL,
	V
>
extends Clearable, IBaseValue<IMPL, V> {
	
	//method declaration
	void addValue(V value);
	
	//method declaration
	IContainer<? extends IMultiValueEntry<IMPL, V>> getRefLocalEntries();
	
	//method declaration
	IContainer<V> getRefValues();
}
