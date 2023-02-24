//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IMultiValue<

	V
>
extends Clearable, IBaseValue<V> {
	
	//method declaration
	void addValue(V value);
	
	//method declaration
	IContainer<? extends IMultiValueEntry<V>> getRefLocalEntries();
	
	//method declaration
	IContainer<V> getRefValues();
	
	//method declaration
	void removeValue(V value);
}
